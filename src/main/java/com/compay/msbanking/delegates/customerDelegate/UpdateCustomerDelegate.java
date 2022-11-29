package com.compay.msbanking.delegates.customerDelegate;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.mapper.factory.CustomerFactory;
import com.compay.msbanking.service.business.CustomerBusinessService;
import com.compay.msbanking.service.functional.CustomerFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("updateCustomerDelegate")
public class UpdateCustomerDelegate implements JavaDelegate {
    private final CustomerBusinessService businessService;
    private final CustomerFunctionalService functionalService;

    public UpdateCustomerDelegate(CustomerBusinessService businessService, CustomerFunctionalService functionalService) {
        this.businessService = businessService;
        this.functionalService = functionalService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            Long id = (Long) execution.getVariable("id");
            Customer customer = functionalService.getCustomerById(id);
            CustomerRequest customerRequest = (CustomerRequest) execution.getVariable("request");
            CustomerFactory.updateCustomer(customer, customerRequest);
            functionalService.updateCustomer(customer);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setData(CustomerFactory.convertCustomerToResponse(customer));
            execution.setVariable("response",baseResponse);
        } catch (BaseException e) {

        }

    }
}
