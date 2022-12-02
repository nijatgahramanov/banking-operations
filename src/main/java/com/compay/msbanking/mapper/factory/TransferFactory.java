package com.compay.msbanking.mapper.factory;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.dto.response.TransferResponse;
import com.compay.msbanking.entity.Transfer;

import java.util.Date;

public class TransferFactory {
    public static Transfer convertRequestToTransfer(TransferRequest request) {
        return new Transfer()
                .setCreditorCardId(request.getCreditorCardId())
                .setCreditorAccountId(request.getCreditorAccountId())
                .setDebitorAccountId(request.getDebitorAccountId())
                .setDebitorCardId(request.getDebitorCardId())
                .setActive(1)
                .setDebitorCurrency(request.getDebitorCurrency())
                .setDebitorAmount(request.getDebitorAmount())
                .setTransferType(request.getTransferType())
                .setCreateDate(new Date());
    }

    public static TransferResponse convertTransferToResponse(Transfer transfer) {
        return new TransferResponse()
                .setCreditorAccountId(transfer.getCreditorAccountId())
                .setCreditorCardId(transfer.getCreditorCardId())
                .setDebitorAmount(transfer.getDebitorAmount())
                .setDebitorCurrency(transfer.getDebitorCurrency())
                .setDebitorCardId(transfer.getDebitorCardId());
    }
}
