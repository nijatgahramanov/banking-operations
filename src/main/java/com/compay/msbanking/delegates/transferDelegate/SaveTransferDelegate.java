package com.compay.msbanking.delegates.transferDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Transfer;
import com.compay.msbanking.enums.TransferStatusEnum;
import com.compay.msbanking.mapper.factory.TransferFactory;
import com.compay.msbanking.service.business.TransferBusinessService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("savaTransferDelegate")
public class SaveTransferDelegate implements JavaDelegate {
    private final TransferBusinessService transferBusinessService;

    public SaveTransferDelegate(TransferBusinessService transferBusinessService) {
        this.transferBusinessService = transferBusinessService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        Transfer transfer = TransferFactory.convertRequestToTransfer(request)
                .setStatus(TransferStatusEnum.CREATED);
        transferBusinessService.saveTransaction(transfer);
    }
}
