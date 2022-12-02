package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.service.business.AccountBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkCreditorAccountById")
public class CheckCreditorAccountByIdDelegate implements JavaDelegate {

    private final AccountBusinessService accountBusinessService;

    public CheckCreditorAccountByIdDelegate(AccountBusinessService accountBusinessService) {
        this.accountBusinessService = accountBusinessService;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        boolean haveCreditorAccount = accountBusinessService.checkAccountAvailable(request.getCreditorAccountId());
        execution.setVariable("haveCreditorAccount",haveCreditorAccount);
    }
}
