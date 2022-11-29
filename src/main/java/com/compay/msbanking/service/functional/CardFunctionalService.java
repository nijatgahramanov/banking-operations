package com.compay.msbanking.service.functional;

import com.compay.msbanking.entity.Card;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CardFunctionalService {

    private final CardRepository cardRepository;

    public CardFunctionalService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card addCard(Card card){
        return cardRepository.save(card);
    }

    public Card getCard(Long id){
        return cardRepository.findByIdAndActive(id,1)
                .orElseThrow(()->(BaseException.of(ErrorEnum.CARD_NOT_FOUND)));
    }

    public List<Card> getCards(){
        return cardRepository.findByActive(1).orElseGet(()->(new ArrayList()));
    }

    public Card updateCard(Card card){
        return cardRepository.save(card);
    }
}
