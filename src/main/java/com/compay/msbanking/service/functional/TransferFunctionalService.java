package com.compay.msbanking.service.functional;

import com.compay.msbanking.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferFunctionalService {

    private final CardFunctionalService cardFunctionalService;


    public TransferFunctionalService( CardFunctionalService cardFunctionalService) {
        this.cardFunctionalService = cardFunctionalService;
    }



}
