package com.compay.msbanking.delegates.cardDelegate;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.CardFunctionalService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkCreditorCardId")
public class CheckCreditorCardIdDelegate implements JavaDelegate {
    private final CardFunctionalService cardFunctionalService;
    private final AccountFunctionalService accountFunctionalService;

    public CheckCreditorCardIdDelegate(CardFunctionalService cardFunctionalService, AccountFunctionalService accountFunctionalService) {
        this.cardFunctionalService = cardFunctionalService;
        this.accountFunctionalService = accountFunctionalService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        TransferRequest request = (TransferRequest) execution.getVariable("request");
        Card creditorCard = cardFunctionalService.getCard(request.getCreditorCardId());
        if (creditorCard!=null){
            request.setCreditorAccountId(creditorCard.getAccount().getId());
        }
        execution.setVariable("creditorCard", creditorCard);
        execution.setVariable("request", request);
    }
}
