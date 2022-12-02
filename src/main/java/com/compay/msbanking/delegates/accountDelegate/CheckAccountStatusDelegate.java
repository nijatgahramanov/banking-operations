package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Transfer;
import com.compay.msbanking.enums.TransferStatusEnum;
import com.compay.msbanking.service.business.AccountBusinessService;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.TransferFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkAccountStatusDelegate")
public class CheckAccountStatusDelegate implements JavaDelegate {
    private final AccountBusinessService accountBusinessService;
    private final AccountFunctionalService accountFunctionalService;
    private final TransferFunctionalService transferFunctionalService;

    public CheckAccountStatusDelegate(AccountBusinessService accountBusinessService, AccountFunctionalService accountFunctionalService, TransferFunctionalService transferFunctionalService) {
        this.accountBusinessService = accountBusinessService;
        this.accountFunctionalService = accountFunctionalService;
        this.transferFunctionalService = transferFunctionalService;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        Account account = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        boolean activeAccount = accountFunctionalService.checkAccountStatus(account);
        execution.setVariable("activeAccount", activeAccount);
    }
}
