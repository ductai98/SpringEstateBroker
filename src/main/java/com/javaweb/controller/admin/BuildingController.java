package com.javaweb.controller.admin;


import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingAddOrUpdateRequest;
import com.javaweb.model.request.BuildingRequestDTO;
import com.javaweb.model.response.BuildingResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value="adminBuildingController")
public class BuildingController {

    private static final Logger log = LogManager.getLogger(BuildingController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;


    @GetMapping(value = "/admin/building-search")
    public ModelAndView adminBuildingSearch(
            @RequestParam Map<String, Object> hashMap,
            @RequestParam(name = "typeCode", required = false) List<String> typeCode,
            HttpServletRequest rawRequest) {
        ModelAndView mav = new ModelAndView("admin/building/search");

        BuildingRequestDTO buildingRequestDTO = buildingService.toBuildingRequestDTO(hashMap, typeCode);
        mav.addObject("searchModel", buildingRequestDTO);
        List<BuildingResponseDTO> responses;
        boolean isStaff = SecurityUtils.getAuthorities().stream().anyMatch(s -> s.contains("STAFF"));
        if (isStaff) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            hashMap.put("staffId", staffId);
            if (typeCode != null && !typeCode.isEmpty()) {
                responses = buildingService.findAllBuilding(hashMap, typeCode);
            } else {
                responses = buildingService.getAll(staffId);
            }
        } else {
            if (typeCode != null && !typeCode.isEmpty()) {
                responses = buildingService.findAllBuilding(hashMap, typeCode);
            } else {
                responses = buildingService.getAll(null);
            }
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

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }

        BuildingDTO buildingDTO;
        try {
            buildingDTO = buildingService.findById(id);
        } catch (IllegalArgumentException exception) {
            log.error("e: ", exception);
            return null;
        }

        mav.addObject("buildingInfo", buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }
}
