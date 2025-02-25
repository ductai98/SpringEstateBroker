package com.javaweb.controller.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "customerControllerOfAdmin"  )
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/admin/customer-search")
    public ModelAndView customerSearchPage(@ModelAttribute("searchModel") CustomerDTO dto) {
        ModelAndView mav = new ModelAndView("admin/customer/search");

        if (SecurityUtils.getAuthorities().contains("STAFF")) {
            dto.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        List<CustomerDTO> customers;
        if (dto.getStaffId() != null) {
            customers = customerService.getAll(dto.getStaffId());
        } else {
            customers = customerService.getAll(null);
        }

        mav.addObject("customers", customers);
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView adminBuildingEdit(@ModelAttribute("customerInfo") CustomerDTO request, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        return mav;
    }
}

