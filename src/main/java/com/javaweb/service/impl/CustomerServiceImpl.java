package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public List<CustomerDTO> getAll(Long staffId) {
        List<CustomerEntity> entities;
        if (staffId != null) {
            UserEntity staff = userRepository.findById(staffId)
                    .orElseThrow(() -> new IllegalArgumentException("staff not found, staffId = " + staffId + " ! "));

            List<UserEntity> staffs = new ArrayList<>();
            staffs.add(staff);
            entities = customerRepository.findAllByStaffsAndStatus(staffs, 1L).stream()
                    .distinct().collect(Collectors.toList());
        } else {
            entities = customerRepository.findAll().stream()
                    .distinct().collect(Collectors.toList());
        }

        List<CustomerDTO> result = new ArrayList<>();

        for (CustomerEntity entity : entities) {
            CustomerDTO dto = customerConverter.toCustomerDTO(entity);
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<CustomerDTO> getAllCustomer(CustomerDTO customer) {
        List<CustomerEntity> entities = customerRepository.findAll(customer);
        List<CustomerDTO> result = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            CustomerDTO dto = customerConverter.toCustomerDTO(entity);
            result.add(dto);
        }
        return result;
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customer) {
        CustomerEntity entity = customerConverter.toCustomerEntity(customer);

        customerRepository.save(entity);
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found, id = " + id + " ! "));
        return customerConverter.toCustomerDTO(entity);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        List<CustomerEntity> entities = customerRepository.findByIdIn(ids);
        entities.forEach(entity -> entity.setStatus(0L));
        customerRepository.saveAll(entities);
    }

    @Override
    public List<StaffResponseDTO> getStaffs(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found, id = " + customerId + " ! "));

        List<UserEntity> staffs = customerEntity.getStaffs();
        List<UserEntity> allStaffs = userRepository.findByStatusAndRolesCode(1, "STAFF");

        List<StaffResponseDTO> result = new ArrayList<>();
        for(UserEntity item : allStaffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(item.getId());
            staffResponseDTO.setFullName(item.getFullName());
            if (staffs.stream().anyMatch(e -> e.getId().equals(item.getId()))){
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            result.add(staffResponseDTO);
        }
        return result;
    }

    @Override
    public CustomerDTO assignStaffs(Long customerId, List<Long> staffIds) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        List<UserEntity> staffs = userRepository.findByIdIn(staffIds);
        customerEntity.setStaffs(staffs);
        customerRepository.save(customerEntity);
        return customerConverter.toCustomerDTO(customerEntity);
    }
}
