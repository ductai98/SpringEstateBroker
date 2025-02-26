package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="customerAPIOfAdmin")
@RequestMapping(value="/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addOrUpdateCustomer(@RequestBody CustomerDTO request) {
        request.setStatus("1");
        customerService.addOrUpdateCustomer(request);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Customer added/updated successfully");
        responseDTO.setData("/admin/customer-search");
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable List<Long> ids){
        System.out.println("deleteCustomer: " + ids);

        customerService.deleteByIds(ids);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Customer deleted successfully");

        return ResponseEntity.ok(responseDTO);
    }
}
