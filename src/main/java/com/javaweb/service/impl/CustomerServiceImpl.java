package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
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
            entities = customerRepository.findAllByStaffs(staffs).stream()
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
}
