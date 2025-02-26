package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll(Long staffId);
    List<CustomerDTO> getAllCustomer(CustomerDTO customer);
    void addOrUpdateCustomer(CustomerDTO customer);
    CustomerDTO findById(Long id);
    void deleteByIds(List<Long> id);
    List<StaffResponseDTO> getStaffs(Long customerId);
    CustomerDTO assignStaffs(Long customerId, List<Long> staffIds);
}
