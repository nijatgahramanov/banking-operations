package com.compay.msbanking.dto.response;

import com.compay.msbanking.enums.AccountStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class AccountResponse implements Serializable {
    private Long id;

    private String iban;

    private String number;

    private CurrencyEnum currency;

    private BigDecimal balance;

    private AccountStatusEnum status;

    private Long customerId;

    private List<CardResponse> cardResponseList;

    public AccountResponse(){

    }

    public AccountResponse(Long id, String iban, String number, CurrencyEnum currency, BigDecimal balance, AccountStatusEnum status, Long customerId) {
        this.id = id;
        this.iban = iban;
        this.number = number;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public AccountResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIban() {
        return iban;
    }

    public AccountResponse setIban(String iban) {
        this.iban = iban;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public AccountResponse setNumber(String number) {
        this.number = number;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public AccountResponse setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountResponse setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public AccountStatusEnum getStatus() {
        return status;
    }

    public AccountResponse setStatus(AccountStatusEnum status) {
        this.status = status;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public AccountResponse setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public List<CardResponse> getCardResponseList() {
        return cardResponseList;
    }

    public AccountResponse setCardResponseList(List<CardResponse> cardResponseList) {
        this.cardResponseList = cardResponseList;
        return this;
    }
}
