package com.compay.msbanking.dto.request;

import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.enums.TransferStatusEnum;
import com.compay.msbanking.enums.TransferTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferRequest implements Serializable {
    private Long debitorCardId;
    private Long creditorCardId;
    private Long debitorAccountId;
    private Long creditorAccountId;
    private BigDecimal debitorAmount;
    private TransferTypeEnum transferType;
    private TransferStatusEnum status;

    public TransferRequest(Long debitorCardId, Long creditorCardId,
                           Long debitorAccountId, Long creditorAccountId,
                           BigDecimal debitorAmount, TransferTypeEnum transferType,
                           TransferStatusEnum status) {
        this.debitorCardId = debitorCardId;
        this.creditorCardId = creditorCardId;
        this.debitorAccountId = debitorAccountId;
        this.creditorAccountId = creditorAccountId;
        this.debitorAmount = debitorAmount;
        this.transferType = transferType;
        this.status = status;
    }

    public TransferRequest() {
    }

    public Long getDebitorCardId() {
        return debitorCardId;
    }

    public TransferRequest setDebitorCardId(Long debitorCardId) {
        this.debitorCardId = debitorCardId;
        return this;
    }

    public Long getCreditorCardId() {
        return creditorCardId;
    }

    public TransferRequest setCreditorCardId(Long creditorCardId) {
        this.creditorCardId = creditorCardId;
        return this;
    }

    public Long getDebitorAccountId() {
        return debitorAccountId;
    }

    public TransferRequest setDebitorAccountId(Long debitorAccountId) {
        this.debitorAccountId = debitorAccountId;
        return this;
    }

    public Long getCreditorAccountId() {
        return creditorAccountId;
    }

    public TransferRequest setCreditorAccountId(Long creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
        return this;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public TransferRequest setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }


    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public TransferRequest setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
        return this;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public TransferRequest setStatus(TransferStatusEnum status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "debitorCardId=" + debitorCardId +
                ", creditorCardId=" + creditorCardId +
                ", debitorAccountId=" + debitorAccountId +
                ", creditorAccountId=" + creditorAccountId +
                ", debitorAmount=" + debitorAmount +
                ", transferType=" + transferType +
                ", status=" + status +
                '}';
    }
}
