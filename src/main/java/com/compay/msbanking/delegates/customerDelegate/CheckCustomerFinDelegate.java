package com.compay.msbanking.delegates.customerDelegate;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.service.business.CustomerBusinessService;
import com.compay.msbanking.service.functional.CustomerFunctionalService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

@Component("checkCustomerDelegate")
public class CheckCustomerFinDelegate implements JavaDelegate {

    private final CustomerBusinessService businessService;


    public CheckCustomerFinDelegate(CustomerBusinessService businessService) {
        this.businessService = businessService;
    }


    @Override
    public void execute(DelegateExecution execution) {
        CustomerRequest request = (CustomerRequest) execution.getVariable("request");
        Customer customer = businessService.checkCustomerFin(request);
        boolean customerStatus = businessService.customerIsNull(customer);
        execution.setVariable("customer",customer);
        execution.setVariable("customerStatus",customerStatus);
    }
}
