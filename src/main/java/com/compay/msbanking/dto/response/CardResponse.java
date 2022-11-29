package com.compay.msbanking.dto.response;

import com.compay.msbanking.enums.CardStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.util.Date;

public class CardResponse {

    private Long id;

    private String number;

    private CurrencyEnum currency;

    private CardStatusEnum status;

    private BigDecimal balance;

    private Date expireDate;

    private Long accountId;

    public CardResponse(){}
    public CardResponse(String number, CurrencyEnum currency, CardStatusEnum status, BigDecimal balance, Date expireDate, Long accountId) {
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

    public CardResponse setNumber(String number) {
        this.number = number;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public CardResponse setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public CardResponse setStatus(CardStatusEnum status) {
        this.status = status;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CardResponse setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public CardResponse setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public CardResponse setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CardResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
