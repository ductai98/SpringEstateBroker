package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAllBuilding(Map<String, Object> searchRequest, List<String> typeCode);
}
