package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll(Long staffId);
    List<CustomerDTO> getAllCustomer(CustomerDTO customer);
    void addOrUpdateCustomer(CustomerDTO customer);
}
