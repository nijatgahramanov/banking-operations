package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.mapper.factory.AccountFactory;
import com.compay.msbanking.service.business.AccountBusinessService;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("updateAccountDelegate")
public class UpdateAccountDelegate implements JavaDelegate {
    private final AccountBusinessService businessService;
    private final AccountFunctionalService functionalService;

    public UpdateAccountDelegate(AccountBusinessService businessService, AccountFunctionalService functionalService) {
        this.businessService = businessService;
        this.functionalService = functionalService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long id = (Long) execution.getVariable("id");
        AccountRequest request = (AccountRequest) execution.getVariable("request");
        execution.setVariable("response", new BaseResponse().setData(businessService.updateAccount(id, request)));
    }
}
