package com.javaweb.service;

import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import com.javaweb.model.request.BuildingRequestDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<StaffResponseDTO> getStaffs(Long buildingId);
    List<BuildingResponseDTO> getAll();
    List<BuildingResponseDTO> findAllBuilding(Map<String, Object> hashMap, List<String> typeCode);
    BuildingRequestDTO toBuildingRequestDTO(Map<String, Object> hashMap, List<String> typeCode);
    Long addOrUpdateBuilding(BuildingAddOrUpdateRequest request);
    void deleteBuilding(List<Long> ids);
}
