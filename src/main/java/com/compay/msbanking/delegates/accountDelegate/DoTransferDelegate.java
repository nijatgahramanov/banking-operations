package com.compay.msbanking.delegates.accountDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.mapper.TransferMapper;
import com.compay.msbanking.service.business.TransferBusinessService;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("doTransferDelegate")
public class DoTransferDelegate implements JavaDelegate {
    private final TransferBusinessService transferBusinessService;
    private final AccountFunctionalService accountFunctionalService;

    public DoTransferDelegate(TransferBusinessService transferBusinessService, AccountFunctionalService accountFunctionalService) {
        this.transferBusinessService = transferBusinessService;
        this.accountFunctionalService = accountFunctionalService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        transferBusinessService.doTransfer(request);
    }
}
