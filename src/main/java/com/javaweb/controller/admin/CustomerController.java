package com.javaweb.controller.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.TransactionTypeDTO;
import com.javaweb.repository.TransactionService;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionTypeService;
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

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping(value = "/admin/customer-search")
    public ModelAndView customerSearchPage(@ModelAttribute("searchModel") CustomerDTO dto) {
        ModelAndView mav = new ModelAndView("admin/customer/search");
        List<CustomerDTO> customers;

        boolean isStaff = SecurityUtils.getAuthorities().stream().anyMatch(s -> s.contains("STAFF"));
        if (isStaff) {
            dto.setStaffId(SecurityUtils.getPrincipal().getId());
        }

        customers = customerService.getAllCustomer(dto);

        mav.addObject("customers", customers);
        mav.addObject("staffs", userService.getStaffs());
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView adminBuildingEdit(@ModelAttribute("customerInfo") CustomerDTO request, HttpServletRequest httpServletRequest) {

        return new ModelAndView("admin/customer/edit");
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

        List<TransactionTypeDTO> transactionTypes = transactionTypeService.getAll();
        List<TransactionDTO> careTransactions;
        List<TransactionDTO> viewTransactions;

        boolean isStaff = SecurityUtils.getAuthorities().stream().anyMatch(s -> s.contains("STAFF"));
        if (isStaff) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            careTransactions = transactionService.findByTypeAndCustomerIdAndStaffId("CARE", customerDTO.getId(), staffId);
            viewTransactions = transactionService.findByTypeAndCustomerIdAndStaffId("VIEW", customerDTO.getId(), staffId);
        } else {
            careTransactions = transactionService.findByTypeAndCustomerId("CARE", customerDTO.getId());
            viewTransactions = transactionService.findByTypeAndCustomerId("VIEW", customerDTO.getId());
        }

        mav.addObject("customerInfo", customerDTO);
        mav.addObject("transactionTypes", transactionTypes);
        mav.addObject("careTransactions", careTransactions);
        mav.addObject("viewTransactions", viewTransactions);
        return mav;
    }
}

