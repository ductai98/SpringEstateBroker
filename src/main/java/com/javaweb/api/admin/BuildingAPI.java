package com.javaweb.api.admin;


import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import com.javaweb.model.request.BuildingAssignRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping(value="/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @PostMapping
    public BuildingAddOrUpdateRequest addOrUpdateBuilding(@RequestBody BuildingAddOrUpdateRequest request){
        return request;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        System.out.println("deleteBuilding: "+ids);
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
    public ResponseDTO assignStaff(@RequestBody BuildingAssignRequest request){
        System.out.println("assignStaff: "+ request.getStaffs());
        return new ResponseDTO();
    }
}
