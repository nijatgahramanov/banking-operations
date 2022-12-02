package com.compay.msbanking.mapper.factory;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.response.AccountResponse;
import com.compay.msbanking.dto.response.CardResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.mapper.AccountMapper;
import com.compay.msbanking.mapper.CustomerMapper;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountFactory {

    public static Account convertRequestToAccount(Customer customer,AccountRequest request){
        Account account = new Account();



            account.setBalance(request.getBalance());
            account.setCurrency(request.getCurrency());
            account.setCustomer(customer);
        return account;
    }

    public static Account updateAccount(Account account, AccountRequest request){
        if(request.getBalance()!=null){
            account.setBalance(request.getBalance());
        }
        if(StringUtils.hasText(request.getCurrency().toString())){
            account.setCurrency(request.getCurrency());
        }
        return account;
    }

    public static AccountResponse convertAccountToResponse(Account account){

        List<CardResponse> cardResponses = new ArrayList<>();
        if(account.getCards()!=null) {
            cardResponses = account.getCards()
                    .stream()
                    .map((Card card) -> (CardFactory.convertCardToResponse(card)))
                    .collect(Collectors.toList());
        }

        return new AccountResponse()
                .setBalance(account.getBalance())
                .setCurrency(account.getCurrency())
                .setIban(account.getIban())
                .setId(account.getId())
                .setNumber(account.getNumber())
                .setStatus(account.getStatus())
                .setCardResponseList(cardResponses)
                .setCustomerId(account.getCustomer().getId());
    }
}
