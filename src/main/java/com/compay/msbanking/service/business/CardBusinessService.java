package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.CardRequest;
import com.compay.msbanking.dto.response.CardResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.mapper.factory.CardFactory;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.CardFunctionalService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardBusinessService {

    private final CardFunctionalService cardFunctionalService;
    private final AccountFunctionalService accountFunctionalService;

    public CardBusinessService(CardFunctionalService cardFunctionalService, AccountFunctionalService accountFunctionalService) {
        this.cardFunctionalService = cardFunctionalService;
        this.accountFunctionalService = accountFunctionalService;
    }

    public CardResponse addCard(CardRequest request){
        Account account = accountFunctionalService.getAccountById(request.getAccountId());
        Card card = CardFactory.convertRequestToCard(account, request);
        return CardFactory.convertCardToResponse(cardFunctionalService.addCard(card));


    }

    public List<CardResponse> getCards(){
        return cardFunctionalService.getCards()
                .stream()
                .map((Card card)->(CardFactory.convertCardToResponse(card)))
                .collect(Collectors.toList());
    }

    public CardResponse getCard(Long id){
        return CardFactory.convertCardToResponse(cardFunctionalService.getCard(id));
    }

    public CardResponse deleteCard(Long id){
        Card card = cardFunctionalService.getCard(id);
        card.setActive(0);
        Card newCard = cardFunctionalService.updateCard(card);
        return CardFactory.convertCardToResponse(newCard);
    }

    public CardResponse updateCard(Long id,CardRequest request){
        Card card = cardFunctionalService.getCard(id);
        Card newCard=CardFactory.updateCard(card,request);
        return CardFactory.convertCardToResponse(cardFunctionalService.updateCard(newCard));
    }
}
