package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.enums.TransferStatusEnum;
import com.compay.msbanking.mapper.factory.TransferFactory;
import com.compay.msbanking.service.business.AccountBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkBalanceDelegate")
public class CheckBalanceDelegate implements JavaDelegate {
    private final AccountBusinessService accountBusinessService;

    public CheckBalanceDelegate(AccountBusinessService accountBusinessService) {
        this.accountBusinessService = accountBusinessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        boolean enoughBalance = accountBusinessService.checkBalance(request.getDebitorAmount(), request);
        execution.setVariable("enoughBalance", enoughBalance);

    }
}
