package com.javaweb.controller.admin;

import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "customerControllerOfAdmin"  )
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/customer-search")
    public ModelAndView customerSearchPage(@ModelAttribute("searchModel") CustomerDTO dto) {
        ModelAndView mav = new ModelAndView("admin/customer/search");
        List<CustomerDTO> customers;
        if (SecurityUtils.getAuthorities().contains("STAFF")) {
            dto.setStaffId(SecurityUtils.getPrincipal().getId());
            customers = customerService.getAll(dto.getStaffId());
        } else {
            customers = customerService.getAllCustomer(dto);
        }

        mav.addObject("customers", customers);
        mav.addObject("staffs", userService.getStaffs());
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView adminBuildingEdit(@ModelAttribute("customerInfo") CustomerDTO request, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView adminBuildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null");
        }

        CustomerDTO customerDTO;
        try {
            customerDTO = customerService.findById(id);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            return null;
        }

        mav.addObject("customerInfo", customerDTO);
        return mav;
    }
}

