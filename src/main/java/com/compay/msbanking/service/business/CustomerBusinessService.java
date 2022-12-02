package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.mapper.CustomerMapper;
import com.compay.msbanking.mapper.factory.AccountFactory;
import com.compay.msbanking.mapper.factory.CustomerFactory;
import com.compay.msbanking.service.functional.CustomerFunctionalService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.commons.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerBusinessService {

    private final CustomerFunctionalService customerFunctionalService;

    public CustomerBusinessService(CustomerFunctionalService customerFunctionalService) {
        this.customerFunctionalService = customerFunctionalService;
    }


    public List<CustomerResponse> getCustomers() {
        List<CustomerResponse> response =customerFunctionalService.getCustomers()
                        .stream()
                        .map((Customer customer)->(CustomerFactory.convertCustomerToResponse(customer)))
                        .collect(Collectors.toList());

        return response;

    }


    public CustomerResponse getCustomerById(Long id) {
        return  CustomerFactory.convertCustomerToResponse(customerFunctionalService.getCustomerById(id));
    }


    public CustomerResponse addCustomer(CustomerRequest request) {
        Customer customer = CustomerFactory.addCustomer(request);
        Customer savedCustomer = customerFunctionalService.addCustomer(customer);
        return CustomerFactory.convertCustomerToResponse(savedCustomer);
    }


    public CustomerResponse updateCustomer(Long id,CustomerRequest customerRequest) {
        Customer customer = customerFunctionalService.getCustomerById(id);
        CustomerFactory.updateCustomer(customer, customerRequest);
        customerFunctionalService.updateCustomer(customer);
        return CustomerFactory.convertCustomerToResponse(customer);
    }

    public CustomerResponse deleteCustomer(Long id){
        Customer customer = customerFunctionalService.getCustomerById(id);
        customer.setActive(0);
        customerFunctionalService.updateCustomer(customer);
        return CustomerFactory.convertCustomerToResponse(customer);
    }

    public Customer checkCustomerFin(CustomerRequest request){
        Customer customer = customerFunctionalService.findCustomerFinCode(request.getFin().trim());
        return customer;
    }

    public boolean customerIsNull(Customer customer){
        if(customer==null)
            return true;
        return false;
    }

}
