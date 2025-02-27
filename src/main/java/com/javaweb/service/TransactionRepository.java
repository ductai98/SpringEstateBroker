package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.TransactionTypeEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCustomerAndTransactionType(CustomerEntity customer, TransactionTypeEntity transactionType);
    List<TransactionEntity> findByCustomerAndTransactionTypeAndStaff(CustomerEntity customer, TransactionTypeEntity transactionType, UserEntity staff);
}
