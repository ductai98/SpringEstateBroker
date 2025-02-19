package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAllBuilding(Map<String, Object> searchRequest, List<String> typeCode);
}
