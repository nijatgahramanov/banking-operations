package com.compay.msbanking.controller.v1;

import com.compay.msbanking.dto.request.CardRequest;
import com.compay.msbanking.service.business.CardBusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardBusinessService cardService;

    public CardController(CardBusinessService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity addCard(@RequestBody CardRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.addCard(request));
    }

    @GetMapping
    public ResponseEntity getCards(){
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCardById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCard(id));
    }

    @DeleteMapping
    public ResponseEntity deleteCard(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cardService.deleteCard(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCard(@PathVariable Long id, @RequestBody CardRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(cardService.updateCard(id,request));
    }
}
