package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkCreditorAccountStatus")
public class CheckCreditorAccountStatusDelegate implements JavaDelegate {
    private final AccountFunctionalService accountFunctionalService;

    public CheckCreditorAccountStatusDelegate(AccountFunctionalService accountFunctionalService) {
        this.accountFunctionalService = accountFunctionalService;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Account creditorAccount = (Account) execution.getVariable("creditorAccount");
        boolean activeCreditorAccount = accountFunctionalService.checkAccountStatus(creditorAccount);
        execution.setVariable("activeCreditorAccount", activeCreditorAccount);
    }
}
