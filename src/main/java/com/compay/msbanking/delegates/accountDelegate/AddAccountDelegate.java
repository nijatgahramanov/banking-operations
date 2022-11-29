package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.service.business.AccountBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("addAccountDelegate")
public class AddAccountDelegate implements JavaDelegate {

    private final AccountBusinessService businessService;

    public AddAccountDelegate(AccountBusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
