package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.dto.response.TransferResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.entity.Transfer;
import com.compay.msbanking.entity.TransferType;
import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.enums.TransferTypeEnum;
import com.compay.msbanking.mapper.factory.AccountFactory;
import com.compay.msbanking.mapper.factory.TransferFactory;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.TransferFunctionalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class TransferBusinessService {
    private final TransferFunctionalService transferFunctionalService;
    private final CardBusinessService cardBusinessService;
    private final AccountFunctionalService accountFunctionalService;


    public TransferBusinessService(TransferFunctionalService transferFunctionalService, CardBusinessService cardBusinessService, AccountFunctionalService accountFunctionalService) {
        this.transferFunctionalService = transferFunctionalService;
        this.cardBusinessService = cardBusinessService;
        this.accountFunctionalService = accountFunctionalService;
    }

    //check debitor card
//    public boolean checkDebitor(TransferRequest request) {
//        if (cardBusinessService.getCard(request.getDebitorCardId()) != null)
//            return true;
//        return false;
//    }

    //change currency for transfer amount
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
        BigDecimal creditChangeAmount = declareAmount(request);
        if (request.getTransferType() == TransferTypeEnum.ACCOUNT_TO_ACCOUNT) {
            changeDebitorBalance(request, request.getDebitorAmount());

            changeCreditorBalance(request, creditChangeAmount); //kocurmeleri test et
        } else {
            changeDebitorCardBalance(request, request.getDebitorAmount());
            changeCreditorCardBalance(request, creditChangeAmount);
        }
    }


    //check transfer type from request (account or card)
    public String checkTransferType(TransferRequest transferRequest) {
        if (transferRequest.getTransferType().name().equals(TransferTypeEnum.ACCOUNT_TO_ACCOUNT.name())) {
            return TransferTypeEnum.ACCOUNT_TO_ACCOUNT.name();
        } else if (transferRequest.getTransferType().name().equals(TransferTypeEnum.CART_TO_CART.name()))
            return TransferTypeEnum.CART_TO_CART.name();
        return null;
    }

    //when transfer account_to_account change debitor balance
    public BigDecimal changeDebitorBalance(TransferRequest request, BigDecimal amount) {

        Account account = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        BigDecimal finalDebitorAccountBalance = account.getBalance().subtract(amount);
        account.setBalance(finalDebitorAccountBalance);
        accountFunctionalService.updateAccount(account);
        return finalDebitorAccountBalance;
    }

    //when transfer account_to_account change creditor balance
    public BigDecimal changeCreditorBalance(TransferRequest request, BigDecimal amount) {
        Account account = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        BigDecimal finalCreditorAccountBalance = account.getBalance().add(amount);
        account.setBalance(finalCreditorAccountBalance);
        accountFunctionalService.updateAccount(account);
        return finalCreditorAccountBalance;
    }

    public BigDecimal changeDebitorCardBalance(TransferRequest request, BigDecimal amount) {
        Account debitorAccount = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        Card debitorCard = cardBusinessService.getCardById(request.getDebitorCardId());

        BigDecimal accountFinalBalance = debitorAccount.getBalance().subtract(amount);
        BigDecimal cardFinalBalance = debitorCard.getBalance().subtract(amount);

        debitorCard.setBalance(cardFinalBalance);
        debitorAccount.setBalance(accountFinalBalance);

        accountFunctionalService.updateAccount(debitorAccount);
        cardBusinessService.changeCardBalance(debitorCard);

        return cardFinalBalance;
    }

    public BigDecimal changeCreditorCardBalance(TransferRequest request, BigDecimal amount) {
        Account creditorAccount = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        Card creditorCard = cardBusinessService.getCardById(request.getCreditorCardId());
        BigDecimal finalAmount = declareAmount(request);

        BigDecimal finalCreditorCardBalance = creditorCard.getBalance().add(finalAmount);
        BigDecimal finalCreditorAccountBalance = creditorAccount.getBalance().add(finalAmount);

        creditorAccount.setBalance(finalCreditorAccountBalance);
        creditorCard.setBalance(finalCreditorCardBalance);

        cardBusinessService.changeCardBalance(creditorCard);
        accountFunctionalService.updateAccount(creditorAccount);

        return finalCreditorCardBalance;
    }


    public BigDecimal declareAmount(TransferRequest request) {
        Account debitorAccount = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        Account creditorAccount = accountFunctionalService.getAccountById(request.getCreditorAccountId());
        BigDecimal convertedAmount = convertCurrency(request.getDebitorAmount(), debitorAccount, creditorAccount);
        return convertedAmount;
    }

    //save all transactions
    public void saveTransaction(Transfer transfer) {
        transferFunctionalService.addTransfer(transfer);
        //return TransferFactory.convertTransferToResponse(transfer);
    }


}
