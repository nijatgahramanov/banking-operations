package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.dto.response.TransferResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Transfer;
import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.mapper.factory.AccountFactory;
import com.compay.msbanking.mapper.factory.TransferFactory;
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

    public void doTransfer(TransferRequest request) {
        changeDebitorBalance(request, request.getDebitorAmount());
        Account debitorAccount = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        Account creditorAccount = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        BigDecimal creditChangeAmount = convertCurrency(request.getDebitorAmount(), debitorAccount, creditorAccount);

        changeCreditorBalance(request, creditChangeAmount);


    }

    public BigDecimal changeDebitorBalance(TransferRequest request, BigDecimal amount) {

        //System.out.println("request amount" + amount);

        Account account = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        BigDecimal finalDebitorAccountBalance = account.getBalance().subtract(amount);
        account.setBalance(finalDebitorAccountBalance);
        //System.out.println("debitor" + finalDebitorAccountBalance);
        accountFunctionalService.updateAccount(account);
        return finalDebitorAccountBalance;
    }

    public BigDecimal changeCreditorBalance(TransferRequest request, BigDecimal amount) {
        Account account = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        BigDecimal finalCreditorAccountBalance = account.getBalance().add(amount);
        account.setBalance(finalCreditorAccountBalance);
        accountFunctionalService.updateAccount(account);
        return finalCreditorAccountBalance;
    }


    public BigDecimal declareAmount(TransferRequest request) {
        Account debitorAccount = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        Account creditorAccount = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        BigDecimal convertedAmount = convertCurrency(request.getDebitorAmount(), debitorAccount, creditorAccount);
        return convertedAmount;
    }

    public void saveTransaction(Transfer transfer) {
        transferFunctionalService.addTransfer(transfer);
        //return TransferFactory.convertTransferToResponse(transfer);
    }


}
