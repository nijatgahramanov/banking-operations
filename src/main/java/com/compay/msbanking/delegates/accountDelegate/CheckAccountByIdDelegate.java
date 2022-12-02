package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.service.business.AccountBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkAccountById")
public class CheckAccountByIdDelegate implements JavaDelegate {
    private final AccountBusinessService accountBusinessService;

    public CheckAccountByIdDelegate(AccountBusinessService accountBusinessService) {
        this.accountBusinessService = accountBusinessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        boolean haveAccount = accountBusinessService.checkAccountAvailable(request.getDebitorAccountId());
        execution.setVariable("haveAccount",haveAccount);
    }
}
