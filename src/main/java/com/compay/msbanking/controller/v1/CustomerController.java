package com.compay.msbanking.controller.v1;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.service.business.CustomerBusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerBusinessService customerService;

    public CustomerController(CustomerBusinessService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    private ResponseEntity addCustomer(@RequestBody CustomerRequest requestCustomer){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse().success(customerService.addCustomer(requestCustomer)));
    }

    @GetMapping
    public ResponseEntity getCustomers(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.getCustomers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.getCustomerById(id)));
    }

    @DeleteMapping
    public ResponseEntity deleteCustomer(@RequestParam("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.deleteCustomer(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id,@RequestBody CustomerRequest requestCustomer){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.updateCustomer(id,requestCustomer)));
    }


}
