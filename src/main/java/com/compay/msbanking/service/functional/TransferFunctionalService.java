package com.compay.msbanking.service.functional;

import com.compay.msbanking.entity.Transfer;
import com.compay.msbanking.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferFunctionalService {

    private final CardFunctionalService cardFunctionalService;
    private final TransferRepository transferRepository;


    public TransferFunctionalService(CardFunctionalService cardFunctionalService, TransferRepository transferRepository) {
        this.cardFunctionalService = cardFunctionalService;
        this.transferRepository = transferRepository;
    }

    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public Transfer updateTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }


}
