package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
    List<CustomerEntity> findAllByStaffsAndStatus(List<UserEntity> staffs, Long status);
    List<CustomerEntity> findByIdIn(Collection<Long> ids);
    List<CustomerEntity> findByStaffs(List<UserEntity> staffs);
}
