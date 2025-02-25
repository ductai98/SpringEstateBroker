package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAllByStaffs(List<UserEntity> staffs);
}
