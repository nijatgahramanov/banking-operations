package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.service.business.AccountBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("updateAccountDelegate")
public class UpdateAccountDelegate implements JavaDelegate {
    private final AccountBusinessService businessService;

    public UpdateAccountDelegate(AccountBusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
