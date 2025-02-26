package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.TransactionTypeEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionService;
import com.javaweb.repository.TransactionTypeRepository;
import com.javaweb.service.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public List<TransactionDTO> findByTypeAndCustomerId(String type, Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        List<TransactionTypeEntity> types = transactionTypeRepository.findByCode(type);

        if (types == null || types.isEmpty()) {
            throw new IllegalArgumentException("Transaction type not found");
        }

        TransactionTypeEntity typeEntity = types.get(0);

        List<TransactionEntity> transactions = transactionRepository.findByCustomerAndTransactionType(customerEntity, typeEntity);
        List<TransactionDTO> result = new ArrayList<>();
        transactions.forEach(transaction -> result.add(transactionConverter.toTransactionDTO(transaction)));
        return result;
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
