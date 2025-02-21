package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.RentTypeEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import com.javaweb.model.request.BuildingRequestDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.RentTypeRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private RentTypeRepository rentTypeRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public void addOrUpdateBuilding(BuildingAddOrUpdateRequest request) {

        BuildingEntity entity = buildingConverter.toBuildingEntity(request);

        if (entity.getId() != null) {
            rentAreaRepository.deleteAllByBuilding(entity);
        }

        List<RentAreaEntity> rentAreaEntities = parseRentArea(request.getRentArea(), entity);
        
        entity.setRentAreas(rentAreaEntities);

        List<String> typeCode = request.getTypeCode();
        Set<RentTypeEntity> updatedRentTypes = parseRentType(typeCode);
        entity.setRentTypes(updatedRentTypes);

        buildingRepository.save(entity);

    }

    List<RentAreaEntity> parseRentArea(String rentArea, BuildingEntity buildingEntity){
        String[] rentAreaRequest = rentArea.replaceAll(" ", "").split(",");
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for (String item : rentAreaRequest){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            if (StringUtils.check(item)) {
                rentAreaEntity.setValue(Long.parseLong(item));
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaEntities.add(rentAreaEntity);
            }
        }
        
        return rentAreaEntities;
    }

    Set<RentTypeEntity> parseRentType(List<String> typeCode) {
        if (typeCode == null || typeCode.isEmpty()){
            return new HashSet<>();
        }

        Set<RentTypeEntity> entities = typeCode.stream()
                .map(code -> rentTypeRepository.findByCode(code)
                        .orElseThrow(() -> new IllegalArgumentException("type code not found, code = " + code + " ! ")))
                .collect(Collectors.toSet());

        return entities;
            
    }

    @Override
    public void deleteBuilding(List<Long> ids) {
        if (ids == null || ids.isEmpty()){
            throw new IllegalArgumentException("Ids can not be null or empty! ");
        }
        buildingRepository.deleteAllByIdIn(ids);
    }

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
        List<BuildingEntity> buildingEntities = buildingRepository.findAll().stream().distinct().collect(Collectors.toList());;
        buildingEntities.forEach(item -> {
            BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
            buildingResponseDTO.setId(item.getId());
            buildingResponseDTO.setName(item.getName());
            String district = "";
            if (item.getDistrict() != null && !item.getDistrict().isEmpty()){
                district = districtCode.valueOf(item.getDistrict()).getDistrictName();
            }
            String rentType = item.getRentTypes().stream().map(
                    type -> type.getName()
            ).collect(Collectors.joining(", "));
            buildingResponseDTO.setRentType(rentType);
            buildingResponseDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district);
            buildingResponseDTO.setFloorArea(item.getFloorArea());
            String rentArea = item.getRentAreas().stream().map(
                    area->area.getValue().toString()
            ).collect(Collectors.joining(","));
            buildingResponseDTO.setRentArea(rentArea);
            buildingResponseDTO.setEmptyArea(item.getEmptyArea());
            buildingResponseDTO.setRentPrice(item.getRentPrice());
            buildingResponseDTO.setManagerName(item.getManagerName());
            buildingResponseDTO.setManagerPhoneNumber(item.getManagerPhoneNumber());
            buildingResponseDTO.setBrokerageFee(item.getBrokerageFee());
            buildingResponseDTO.setNumberOfBasement(item.getNumberOfBasement());
            result.add(buildingResponseDTO);
        });
        return result;
    }

    @Override
    public List<BuildingResponseDTO> findAllBuilding(Map<String, Object> hashMap, List<String> typeCode) {
        List<BuildingEntity> entities = buildingRepository.findAllBuilding(hashMap,typeCode).stream().distinct().collect(Collectors.toList());
        List<BuildingResponseDTO> result = new ArrayList<>();
        entities.forEach(item -> {
            BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
            buildingResponseDTO.setId(item.getId());
            buildingResponseDTO.setName(item.getName());
            String rentType = item.getRentTypes().stream().map(
                    type -> type.getName()
            ).collect(Collectors.joining(", "));
            buildingResponseDTO.setRentType(rentType);

            String district = "";
            if (item.getDistrict() != null && !item.getDistrict().isEmpty()){
                district = districtCode.valueOf(item.getDistrict()).getDistrictName();
            }
            buildingResponseDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district);
            buildingResponseDTO.setFloorArea(item.getFloorArea());
            String rentArea = item.getRentAreas().stream().map(
                    area->area.getValue().toString()
            ).collect(Collectors.joining(", "));
            buildingResponseDTO.setRentArea(rentArea);
            buildingResponseDTO.setEmptyArea(item.getEmptyArea());
            buildingResponseDTO.setRentPrice(item.getRentPrice());
            buildingResponseDTO.setManagerName(item.getManagerName());
            buildingResponseDTO.setManagerPhoneNumber(item.getManagerPhoneNumber());
            buildingResponseDTO.setBrokerageFee(item.getBrokerageFee());
            buildingResponseDTO.setNumberOfBasement(item.getNumberOfBasement());
            result.add(buildingResponseDTO);
        });
        return result;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity entity = buildingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id = " + id + " is not found"));

        List<String> typeCodes = entity.getRentTypes().stream()
                .map(RentTypeEntity::getCode).collect(Collectors.toList());

        BuildingDTO buildingDTO = buildingConverter.toBuildingDTO(entity);
        buildingDTO.setTypeCode(typeCodes);

        String rentArea = rentAreaRepository.findAllByBuilding(entity).stream()
                .map(RentAreaEntity::getValue).map(String::valueOf)
                .collect(Collectors.joining(","));

        buildingDTO.setRentArea(rentArea);
        return buildingDTO;
    }

    @Override
    public BuildingDTO assignStaffs(Long buildingId, List<Long> staffIds) {
        BuildingEntity entity = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Id = " + buildingId + " is not found"));
        List<UserEntity> staffEntities = userRepository.findByIdIn(staffIds);
        entity.setStaffs(staffEntities);
        buildingRepository.save(entity);
        return buildingConverter.toBuildingDTO(entity);
    }

    @Override
    public BuildingRequestDTO toBuildingRequestDTO(Map<String, Object> hashMap, List<String> typeCode) {
        return buildingConverter.toBuildingRequestDTO(hashMap,typeCode);
    }

    @Override
    public BuildingDTO toBuildingDTO(BuildingEntity entity) {
        return buildingConverter.toBuildingDTO(entity);
    }
}
