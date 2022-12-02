package com.compay.msbanking.dto.response;

import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.enums.TransferStatusEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferResponse implements Serializable {

    private Long debitorCardId;
    private Long creditorCardId;
    private Long debitorAccountId;
    private Long creditorAccountId;
    private BigDecimal debitorAmount;
    private CurrencyEnum debitorCurrency;
    private TransferStatusEnum status;

    public TransferResponse(Long debitorCardId, Long creditorCardId,
                            Long debitorAccountId, Long creditorAccountId,
                            BigDecimal debitorAmount, CurrencyEnum debitorCurrency, TransferStatusEnum status) {
        this.debitorCardId = debitorCardId;
        this.creditorCardId = creditorCardId;
        this.debitorAccountId = debitorAccountId;
        this.creditorAccountId = creditorAccountId;
        this.debitorAmount = debitorAmount;
        this.debitorCurrency = debitorCurrency;
        this.status = status;
    }

    public TransferResponse() {
    }

    public Long getDebitorCardId() {
        return debitorCardId;
    }

    public TransferResponse setDebitorCardId(Long debitorCardId) {
        this.debitorCardId = debitorCardId;
        return this;
    }

    public Long getCreditorCardId() {
        return creditorCardId;
    }

    public TransferResponse setCreditorCardId(Long creditorCardId) {
        this.creditorCardId = creditorCardId;
        return this;
    }

    public Long getDebitorAccountId() {
        return debitorAccountId;
    }

    public TransferResponse setDebitorAccountId(Long debitorAccountId) {
        this.debitorAccountId = debitorAccountId;
        return this;
    }

    public Long getCreditorAccountId() {
        return creditorAccountId;
    }

    public TransferResponse setCreditorAccountId(Long creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
        return this;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public TransferResponse setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public TransferResponse setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
        return this;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public TransferResponse setStatus(TransferStatusEnum status) {
        this.status = status;
        return this;
    }
}
