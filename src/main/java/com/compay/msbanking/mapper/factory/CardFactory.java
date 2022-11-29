package com.compay.msbanking.mapper.factory;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.request.CardRequest;
import com.compay.msbanking.dto.response.AccountResponse;
import com.compay.msbanking.dto.response.CardResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import org.springframework.util.StringUtils;

public class CardFactory {

    public static Card convertRequestToCard(Account account, CardRequest request){
        Card card = new Card();

        if(!(StringUtils.hasText(request.getNumber()))){
            throw BaseException.of(ErrorEnum.INVALID_REQUEST);
        }

        card.setBalance(request.getBalance());
        card.setCurrency(request.getCurrency());
        card.setExpireDate(request.getExpireDate());
        card.setStatus(request.getStatus());
        card.setNumber(request.getNumber());
        card.setAccount(account);
        return card;
    }

    public static Card updateCard(Card card, CardRequest request) {
        if (request.getBalance() != null) {
            card.setBalance(request.getBalance());
        }
        if (StringUtils.hasText(request.getCurrency().toString())) {
            card.setCurrency(request.getCurrency());
        }
        if (StringUtils.hasText(request.getExpireDate().toString())) {
            card.setExpireDate(request.getExpireDate());
        }
        if (StringUtils.hasText(request.getStatus().toString())) {
            card.setStatus(request.getStatus());
        }
        if (StringUtils.hasText(request.getNumber())) {
            card.setNumber(request.getNumber());
        }
        return card;
    }
    public static CardResponse convertCardToResponse(Card card){

        return new CardResponse().setBalance(card.getBalance())
                .setCurrency(card.getCurrency())
                .setExpireDate(card.getExpireDate())
                .setId(card.getId())
                .setNumber(card.getNumber())
                .setStatus(card.getStatus())
                .setAccountId(card.getAccount().getId());
    }



}
