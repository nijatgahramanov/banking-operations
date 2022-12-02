package com.compay.msbanking.delegates.cardDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.service.functional.CardFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkCreditorCardId")
public class CheckCreditorCardIdDelegate implements JavaDelegate {
    private final CardFunctionalService cardFunctionalService;

    public CheckCreditorCardIdDelegate(CardFunctionalService cardFunctionalService) {
        this.cardFunctionalService = cardFunctionalService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        Card card = cardFunctionalService.getCard(request.getCreditorCardId());
        execution.setVariable("creditorCard", card);
    }
}
