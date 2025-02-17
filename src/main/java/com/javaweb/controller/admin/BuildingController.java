package com.javaweb.controller.admin;


import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="adminBuildingController")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/admin/building-search")
    public ModelAndView adminBuildingSearch(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/search");
        mav.addObject("searchModel", buildingSearchRequest);

        List<BuildingSearchResponse> responses = new ArrayList<>();
        BuildingSearchResponse response1 = new BuildingSearchResponse();
        response1.setId(3L);
        response1.setName("Building Bitexco");
        response1.setAddress("142 Huỳnh Văn Bánh, Phường 12, Quận Phú Nhuận");
        response1.setNumberOfBasement(1L);
        response1.setFloorArea(100L);
        response1.setRentArea("100, 200, 300");
        response1.setManagerName("Lâm Đức Tài");
        response1.setManagerPhone("0909090909");
        responses.add(response1);
        BuildingSearchResponse response2 = new BuildingSearchResponse();
        response2.setId(5L);
        response2.setName("Building Tower");
        response2.setAddress("125 Trần Huy Liệu, Phường 3, Quận 3");
        response2.setNumberOfBasement(2L);
        response2.setFloorArea(200L);
        response2.setRentArea("200, 300");
        response2.setManagerName("Lâm Đức Anh");
        response2.setManagerPhone("0808080808");
        responses.add(response2);

        mav.addObject("buildings", responses);
        mav.addObject("staffs", userService.getStaffs());
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView adminBuildingEdit(@ModelAttribute("buildingInfo") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        return mav;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView adminBuildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");

        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        mav.addObject("buildingInfo", buildingDTO);
        return mav;
    }
}
