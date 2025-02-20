package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    @Override
    BuildingEntity getOne(Long id);

    @Override
    Optional<BuildingEntity> findById(Long aLong);

    @Override
    List<BuildingEntity> findAll();

    void deleteAllByIdIn(Collection<Long> ids);
}
