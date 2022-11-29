package com.compay.msbanking.delegates.customerDelegate;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.service.business.CustomerBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("addCustomerDelegate")
public class CustomerAddDelegate implements JavaDelegate {

    private final CustomerBusinessService businessService;

    public CustomerAddDelegate(CustomerBusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        CustomerRequest request = (CustomerRequest) execution.getVariable("request");
        CustomerResponse customerResponse = businessService.addCustomer(request);
        BaseResponse response = new BaseResponse();
        response.setData(customerResponse);
        execution.setVariable("response", response);
    }
}
