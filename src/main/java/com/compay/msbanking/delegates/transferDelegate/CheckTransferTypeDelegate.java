package com.compay.msbanking.delegates.transferDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.service.business.TransferBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkTransferType")
public class CheckTransferTypeDelegate implements JavaDelegate {
    private final TransferBusinessService transferBusinessService;

    public CheckTransferTypeDelegate(TransferBusinessService transferBusinessService) {
        this.transferBusinessService = transferBusinessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest transferRequest = (TransferRequest) execution.getVariable("request");
        String transferType = transferBusinessService.checkTransferType(transferRequest);
        execution.setVariable("transferType", transferType);
    }
}
