package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.CardRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.dto.response.CardResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Card;
import com.compay.msbanking.mapper.CardMapper;
import com.compay.msbanking.mapper.factory.CardFactory;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.CardFunctionalService;
import com.compay.msbanking.service.functional.TransferFunctionalService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardBusinessService {

    private final CardFunctionalService cardFunctionalService;
    private final AccountFunctionalService accountFunctionalService;
    private final TransferFunctionalService transferFunctionalService;

    public CardBusinessService(CardFunctionalService cardFunctionalService, AccountFunctionalService accountFunctionalService, TransferFunctionalService transferFunctionalService) {
        this.cardFunctionalService = cardFunctionalService;
        this.accountFunctionalService = accountFunctionalService;
        this.transferFunctionalService = transferFunctionalService;
    }

    public BaseResponse addCard(CardRequest request) {
        BaseResponse response = new BaseResponse();
        Account account = accountFunctionalService.getAccountById(request.getAccountId());
        Card card = CardFactory.convertRequestToCard(account, request);
        CardFactory.convertCardToResponse(cardFunctionalService.addCard(card));
        response.setData(card);
        response.setMessage("card successfully added");
        return response;
    }

    public BaseResponse getCards() {
        BaseResponse response = new BaseResponse();
        List<CardResponse> cardResponseList = cardFunctionalService.getCards()
                .stream()
                .map((Card card) -> (CardFactory.convertCardToResponse(card)))
                .collect(Collectors.toList());
        response.setData(cardResponseList);
        return response;
    }

    public BaseResponse getCard(Long id) {
        BaseResponse response = new BaseResponse();
        CardResponse cardResponse = CardMapper.INSTANCE.cardToCardResponse(cardFunctionalService.getCard(id));
        response.setData(cardResponse);
        return response;
    }

    public Card getCardById(Long id) {
        Card card = cardFunctionalService.getCard(id);
        return card;
    }

    //transfer zamani card var mi yoxlamaq ucundur
    public boolean checkCardAvailable(Long id) {
        if (cardFunctionalService.getCard(id) != null)
            return true;
        return false;
    }

//    public boolean checkBalance(Long id) {
//        Card card = cardFunctionalService.getCard(id);
//        if(card.getBalance()>)
//    }

    public boolean checkCardAvailable(Card card) {
        if (cardFunctionalService.getCard(card.getId()) != null)
            return true;
        return false;
    }

    public BaseResponse deleteCard(Long id) {
        BaseResponse response = new BaseResponse();
        Card card = cardFunctionalService.getCard(id);
        card.setActive(0);
        Card newCard = cardFunctionalService.updateCard(card);
        CardFactory.convertCardToResponse(newCard);
        response.setMessage("Customer succesfully deleted");
        response.setCode(200);
        return response;
    }

    public BaseResponse updateCard(Long id, CardRequest request) {
        BaseResponse response = new BaseResponse();
        Card card = cardFunctionalService.getCard(id);
        Card newCard = CardFactory.updateCard(card, request);
        CardFactory.convertCardToResponse(cardFunctionalService.updateCard(newCard));
        response.setData(newCard);
        response.setMessage("card succesfully updated");
        return response;
    }
}
