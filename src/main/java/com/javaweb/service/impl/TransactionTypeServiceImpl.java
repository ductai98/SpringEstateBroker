package com.javaweb.service.impl;

import com.javaweb.converter.TransactionTypeConverter;
import com.javaweb.entity.TransactionTypeEntity;
import com.javaweb.model.dto.TransactionTypeDTO;
import com.javaweb.repository.TransactionTypeRepository;
import com.javaweb.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionTypeConverter transactionTypeConverter;

    @Override
    public List<TransactionTypeDTO> getAll() {
        List<TransactionTypeEntity> entities = transactionTypeRepository.findAll();
        List<TransactionTypeDTO> dtos = entities.stream()
                .map(entity -> transactionTypeConverter.toTransactionTypeDTO(entity))
                .collect(Collectors.toList());
        return dtos;
    }
}
