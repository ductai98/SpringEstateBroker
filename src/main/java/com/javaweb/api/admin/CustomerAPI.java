package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="customerAPIOfAdmin")
@RequestMapping(value="/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addOrUpdateCustomer(@RequestBody CustomerDTO request) {
        customerService.addOrUpdateCustomer(request);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Customer added/updated successfully");
        responseDTO.setData("/admin/customer-search");
        return ResponseEntity.ok(responseDTO);
    }
}
