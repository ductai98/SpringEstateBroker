package com.javaweb.api.admin;


import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping(value="/api/building")
public class BuildingAPI {

    @PostMapping
    public BuildingAddOrUpdateRequest addOrUpdateBuilding(@RequestBody BuildingAddOrUpdateRequest request){
        return request;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){

    }
}
