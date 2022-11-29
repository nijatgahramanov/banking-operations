package com.compay.msbanking.delegates.customerDelegate;

import com.compay.msbanking.service.business.CustomerBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("findCustomerById")
public class CustomerFindByIdDelegate implements JavaDelegate {

    private final CustomerBusinessService customerBusinessService;

    public CustomerFindByIdDelegate(CustomerBusinessService customerBusinessService) {
        this.customerBusinessService = customerBusinessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //customerBusinessService.getCustomerById()
    }
}
