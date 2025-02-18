package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<StaffResponseDTO> getStaffs(Long buildingId) {

        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if (buildingEntity == null) {
            return null;
        }

        List<UserEntity> staffs = buildingEntity.getStaffs();

        List<UserEntity> allStaffs = userRepository.findByStatusAndRolesCode(1, "STAFF");

        List<StaffResponseDTO> result = new ArrayList<>();
        for(UserEntity item : allStaffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(item.getId());
            staffResponseDTO.setFullName(item.getFullName());
            if (staffs.stream().anyMatch(e -> e.getId().equals(item.getId()))){
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            result.add(staffResponseDTO);
        }
        return result;
    }

    @Override
    public List<BuildingResponseDTO> getAll() {
        List<BuildingResponseDTO> result = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        buildingEntities.forEach(item -> {
            BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
            buildingResponseDTO.setId(item.getId());
            buildingResponseDTO.setName(item.getName());
            String district = districtCode.valueOf(item.getDistrict()).getDistrictName();
            buildingResponseDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district);
            buildingResponseDTO.setFloorArea(item.getFloorArea());
            buildingResponseDTO.setRentArea(item.getRentArea());
            buildingResponseDTO.setEmptyArea(item.getEmptyArea());
            buildingResponseDTO.setRentPrice(item.getRentPrice());
            buildingResponseDTO.setManagerName(item.getManagerName());
            buildingResponseDTO.setManagerPhoneNumber(item.getManagerPhone());
            buildingResponseDTO.setBrokerageFee(item.getBrokerageFee());
            buildingResponseDTO.setNumberOfBasement(item.getNumberOfBasement());
            result.add(buildingResponseDTO);
        });
        return result;
    }
}
