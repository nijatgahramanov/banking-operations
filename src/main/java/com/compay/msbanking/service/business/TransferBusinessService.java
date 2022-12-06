package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.entity.Transfer;
import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.enums.TransferTypeEnum;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.CardFunctionalService;
import com.compay.msbanking.service.functional.TransferFunctionalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferBusinessService {
    private final TransferFunctionalService transferFunctionalService;
    private final CardBusinessService cardBusinessService;
    private final AccountFunctionalService accountFunctionalService;
    private final CardFunctionalService cardFunctionalService;


    public TransferBusinessService(TransferFunctionalService transferFunctionalService, CardBusinessService cardBusinessService, AccountFunctionalService accountFunctionalService, CardFunctionalService cardFunctionalService) {
        this.transferFunctionalService = transferFunctionalService;
        this.cardBusinessService = cardBusinessService;
        this.accountFunctionalService = accountFunctionalService;
        this.cardFunctionalService = cardFunctionalService;
    }

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

    //this method provide all transaction
    public void doTransfer(TransferRequest request) {
        BigDecimal creditChangeAmount = declareAmount(request);
        changeDebitorBalance(request, request.getDebitorAmount());
        changeCreditorBalance(request, creditChangeAmount); //kocurmeleri test et
        changeDebitorCardBalance(request, request.getDebitorAmount());
        changeCreditorCardBalance(request, creditChangeAmount);

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

    //cardLar account un balansi ile ishlesin
    public BigDecimal changeDebitorCardBalance(TransferRequest request, BigDecimal amount) {
        BigDecimal cardAmount = accountFunctionalService.getAccountById(request.getDebitorAccountId()).getBalance();
                //cardBusinessService.getCardById(request.getDebitorCardId()).getBalance().subtract(amount);
        List<Card> cardList = cardBusinessService.getCardByAccountId(request.getDebitorAccountId());

        cardList.stream()
                .map((Card card) -> {
                    card.setBalance(cardAmount);
                    return card;
                }).collect(Collectors.toList());

        cardList.stream()
                .map((Card card) -> cardBusinessService.changeCardBalance(card));
        return cardAmount;
    }

    //this method change creditorCard balance
    public BigDecimal changeCreditorCardBalance(TransferRequest request, BigDecimal amount) {
        BigDecimal cardAmount = accountFunctionalService.getAccountById(request.getCreditorAccountId()).getBalance();
        List<Card> cardList = cardBusinessService.getCardByAccountId(request.getCreditorAccountId());

        cardList.stream().map((Card card) -> {
            card.setBalance(cardAmount);
            return card;
        }).collect(Collectors.toList());

        cardList.stream()
                .map((Card card) -> cardBusinessService.changeCardBalance(card));
        return cardAmount;
    }

//    public BigDecimal foo(TransferRequest request) {
//        Account account = accountFunctionalService.getAccountById(request.getDebitorAccountId());
//        BigDecimal finalAmount = declareAmount(request);
//        BigDecimal cardAmount = cardBusinessService.getCardById(request.getCreditorCardId()).getBalance().add(finalAmount);
//        List<Card> cardList = cardBusinessService.getCardByAccountId(request.getCreditorCardId());
//
//         cardList.stream().map((Card card)->{
//            card.setBalance(cardAmount);
//            return card;
//        }).collect(Collectors.toList());
//
//         cardList.stream().map((Card card)->cardBusinessService.changeCardBalance(card));
//
//
//        return null;
//    }


    //this method declare final amount for creditor account or creditorCard
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
