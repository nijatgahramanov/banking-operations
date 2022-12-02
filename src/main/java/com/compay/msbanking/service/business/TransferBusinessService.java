package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.TransferFunctionalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferBusinessService {
    private final TransferFunctionalService transferFunctionalService;
    private final CustomerBusinessService customerBusinessService;
    private final CardBusinessService cardBusinessService;
    private final AccountFunctionalService accountFunctionalService;


    public TransferBusinessService(TransferFunctionalService transferFunctionalService, CustomerBusinessService customerBusinessService, CardBusinessService cardBusinessService, AccountFunctionalService accountFunctionalService) {
        this.transferFunctionalService = transferFunctionalService;
        this.customerBusinessService = customerBusinessService;
        this.cardBusinessService = cardBusinessService;
        this.accountFunctionalService = accountFunctionalService;
    }

    public boolean checkDebitor(TransferRequest request) {
        if (cardBusinessService.getCard(request.getDebitorCardId()) != null)
            return true;
        return false;
    }

    public BigDecimal convertCurrency(BigDecimal amount, Account debitorAccount, Account creditorAccount) {
        if (debitorAccount.getCurrency() == CurrencyEnum.AZN && creditorAccount.getCurrency() == CurrencyEnum.USD) {
            return amount.multiply(new BigDecimal(0.59));
        } else if (debitorAccount.getCurrency() == CurrencyEnum.USD && creditorAccount.getCurrency() == CurrencyEnum.AZN) {
            return amount.multiply(new BigDecimal(1.7));
        } else {
            return amount;
        }
    }

    public BigDecimal declareAmount(TransferRequest request) {
        Account debitorAccount = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        Account creditorAccount = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        BigDecimal convertedAmount = convertCurrency(request.getDebitorAmount(), debitorAccount, creditorAccount);
        return convertedAmount;
    }


}
