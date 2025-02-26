package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.AssignRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
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

    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffs(@PathVariable("id") Long id){
        List<StaffResponseDTO> staffs = customerService.getStaffs(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffs);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @PostMapping("assign")
    public ResponseDTO assignStaff(@RequestBody AssignRequest request){
        CustomerDTO buildingDTO = customerService.assignStaffs(request.getId(), request.getStaffIds());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(buildingDTO);
        return responseDTO;
    }

    @PostMapping("/transaction")
    public ResponseDTO addTransaction(@RequestBody TransactionDTO transaction){
        TransactionDTO transactionDTO = customerService.addTransaction(transaction);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(transactionDTO);
        return responseDTO;
    }
}
