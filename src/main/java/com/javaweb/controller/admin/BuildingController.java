package com.javaweb.controller.admin;


import com.javaweb.converter.BuildingConverter;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import com.javaweb.model.request.BuildingRequestDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value="adminBuildingController")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBuildingService buildingService;


    @GetMapping(value = "/admin/building-search")
    public ModelAndView adminBuildingSearch(
            @RequestParam Map<String, Object> hashMap,
            @RequestParam(name = "typeCode", required = false) List<String> typeCode,
            HttpServletRequest rawRequest) {
        ModelAndView mav = new ModelAndView("admin/building/search");

        BuildingRequestDTO buildingRequestDTO = buildingService.toBuildingRequestDTO(hashMap, typeCode);
        mav.addObject("searchModel", buildingRequestDTO);
        List<BuildingResponseDTO> responses;
        if (typeCode != null && !typeCode.isEmpty()) {
            responses = buildingService.findAllBuilding(hashMap, typeCode);
        } else {
            responses = buildingService.getAll();
        }

        mav.addObject("buildings", responses);
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView adminBuildingEdit(@ModelAttribute("buildingInfo") BuildingAddOrUpdateRequest request, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView adminBuildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        mav.addObject("buildingInfo", buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }
}
