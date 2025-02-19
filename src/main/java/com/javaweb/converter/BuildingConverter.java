package com.javaweb.converter;

import com.javaweb.model.request.BuildingRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingConverter {
    public BuildingRequestDTO toBuildingRequestDTO(Map<String, Object> hashMap, List<String> typeCode){
        BuildingRequestDTO buildingRequestDTO = new BuildingRequestDTO();
        if (hashMap.get("name") != null && !"".equals(hashMap.get("name"))) {
            buildingRequestDTO.setName((String)hashMap.get("name"));
        }
        if (hashMap.get("floorArea") != null && !"".equals(hashMap.get("floorArea"))) {
            buildingRequestDTO.setFloorArea(Long.parseLong((String) hashMap.get("floorArea")));
        }
        if (hashMap.get("district") != null && !"".equals(hashMap.get("district"))) {
            buildingRequestDTO.setDistrict((String)hashMap.get("district"));
        }
        if (hashMap.get("ward") != null && !"".equals(hashMap.get("ward"))) {
            buildingRequestDTO.setWard((String)hashMap.get("ward"));
        }
        if (hashMap.get("street") != null && !"".equals(hashMap.get("street"))) {
            buildingRequestDTO.setStreet((String)hashMap.get("street"));
        }
        if (hashMap.get("numberOfBasement") != null && !"".equals(hashMap.get("numberOfBasement"))) {
            buildingRequestDTO.setNumberOfBasement(Long.parseLong((String) hashMap.get("numberOfBasement")));
        }
        if (hashMap.get("direction") != null && !"".equals(hashMap.get("direction"))) {
            buildingRequestDTO.setDirection((String)hashMap.get("direction"));
        }

        if (hashMap.get("level") != null && !"".equals(hashMap.get("level"))) {
            buildingRequestDTO.setLevel(Long.parseLong((String) hashMap.get("level")));
        }
        if (hashMap.get("fromArea") != null && !"".equals(hashMap.get("fromArea"))) {
            buildingRequestDTO.setFromArea(Long.parseLong((String) hashMap.get("fromArea")));
        }
        if (hashMap.get("toArea") != null && !"".equals(hashMap.get("toArea"))) {
            buildingRequestDTO.setToArea(Long.parseLong((String)hashMap.get("toArea")));
        }
        if (hashMap.get("fromPrice") != null && !"".equals(hashMap.get("fromPrice"))) {
            buildingRequestDTO.setFromPrice(Long.parseLong((String)hashMap.get("fromPrice")));
        }

        if (hashMap.get("toPrice") != null && !"".equals(hashMap.get("toPrice"))) {
            buildingRequestDTO.setToPrice(Long.parseLong((String)hashMap.get("toPrice")));
        }
        if (hashMap.get("managerName") != null && !"".equals(hashMap.get("managerName"))) {
            buildingRequestDTO.setManagerName((String)hashMap.get("managerName"));
        }
        if (hashMap.get("managerPhone") != null && !"".equals(hashMap.get("managerPhone"))) {
            buildingRequestDTO.setManagerPhone((String)hashMap.get("managerPhone"));
        }
        if (hashMap.get("staffId") != null && !"".equals(hashMap.get("staffId"))) {
            buildingRequestDTO.setStaffId(Long.parseLong((String)hashMap.get("staffId")));
        }

        if (typeCode != null && !typeCode.isEmpty()) {
            buildingRequestDTO.setTypeCode(typeCode);
        }

        return buildingRequestDTO;
    }
}
