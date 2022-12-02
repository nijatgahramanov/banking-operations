package com.compay.msbanking.controller.v2;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.service.business.CustomerBusinessService;
import com.compay.msbanking.util.CamundaUtil;
import org.camunda.bpm.engine.RuntimeService;
import org.glassfish.jersey.internal.guava.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController("customerControllerV2")
@RequestMapping("/api/v2/customers")
public class CustomerController {

    private final CustomerBusinessService customerService;
    private final CamundaUtil camundaUtil;
    private final RuntimeService runtimeService;

    public CustomerController(CustomerBusinessService customerService, CamundaUtil camundaUtil, RuntimeService runtimeService) {
        this.customerService = customerService;
        this.camundaUtil = camundaUtil;
        this.runtimeService = runtimeService;
    }

    @PostMapping
    private BaseResponse addCustomer(@RequestBody CustomerRequest requestCustomer) {
        return camundaUtil.execute("addCustomer", requestCustomer, runtimeService, BaseResponse.class);
    }

    @GetMapping
    public ResponseEntity getCustomers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.getCustomers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.getCustomerById(id)));
    }

    @DeleteMapping
    public ResponseEntity deleteCustomer(@RequestParam("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(customerService.deleteCustomer(id)));
    }

    @PutMapping
    public BaseResponse updateCustomer(@RequestParam Long id, @RequestBody CustomerRequest request) {
        Map maps = new LinkedHashMap();
        maps.put("id", id);
        maps.put("request", request);
        return camundaUtil.execute("updateCustomer", maps, runtimeService, BaseResponse.class);
    }

//    @DeleteMapping
//    public BaseResponse deleteCustomerById(@RequestParam Long id) {
//        Map maps = new LinkedHashMap();
//        maps.put("id", id);
//        return camundaUtil.execute("deleteCustomer", maps, runtimeService, BaseResponse.class);
//    }


}
