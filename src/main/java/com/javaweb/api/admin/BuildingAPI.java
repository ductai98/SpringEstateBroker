package com.javaweb.api.admin;


import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import com.javaweb.model.request.AssignRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping(value="/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addOrUpdateBuilding(@RequestBody BuildingAddOrUpdateRequest request) {
        buildingService.addOrUpdateBuilding(request);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Building added/updated successfully");
        responseDTO.setData("/admin/building-search"); // Include the redirect URL in the response
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<ResponseDTO> deleteBuilding(@PathVariable List<Long> ids){
        System.out.println("deleteBuilding: "+ids);

        buildingService.deleteBuilding(ids);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Buildings deleted successfully");

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffs(@PathVariable("id") Long id){
        List<StaffResponseDTO> staffs = buildingService.getStaffs(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffs);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @PostMapping("assign")
    public ResponseDTO assignStaff(@RequestBody AssignRequest request){
        BuildingDTO buildingDTO = buildingService.assignStaffs(request.getId(), request.getStaffIds());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(buildingDTO);
        return responseDTO;
    }
}
