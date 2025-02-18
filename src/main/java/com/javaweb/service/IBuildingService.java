package com.javaweb.service;

import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface IBuildingService {
    List<StaffResponseDTO> getStaffs(Long buildingId);
    List<BuildingResponseDTO> getAll();
}
