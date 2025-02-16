package com.javaweb.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value="adminBuildingController")
public class BuildingController {
    @GetMapping(value = "/admin/building-list")
    public ModelAndView adminBuildingList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView adminBuildingEdit(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        return mav;
    }
}
