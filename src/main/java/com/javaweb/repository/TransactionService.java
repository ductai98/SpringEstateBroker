package com.javaweb.repository;

import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> findByTypeAndCustomerId(String type, Long customerId);
    void deleteById(Long id);
}
