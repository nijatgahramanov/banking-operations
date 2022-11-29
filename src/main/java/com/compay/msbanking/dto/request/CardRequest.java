package com.compay.msbanking.dto.request;

import com.compay.msbanking.enums.CardStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.util.Date;

public class CardRequest {

    private String number;

    private CurrencyEnum currency;

    private CardStatusEnum status;

    private BigDecimal balance;

    private Date expireDate;

    private Long accountId;

    public CardRequest(String number, CurrencyEnum currency, CardStatusEnum status, BigDecimal balance, Date expireDate, Long accountId) {
        this.number = number;
        this.currency = currency;
        this.status = status;
        this.balance = balance;
        this.expireDate = expireDate;
        this.accountId = accountId;
    }

    public String getNumber() {
        return number;
    }

    public CardRequest setNumber(String number) {
        this.number = number;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public CardRequest setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public CardRequest setStatus(CardStatusEnum status) {
        this.status = status;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CardRequest setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public CardRequest setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public CardRequest setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
}
