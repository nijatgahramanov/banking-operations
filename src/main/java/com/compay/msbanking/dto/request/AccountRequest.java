package com.compay.msbanking.dto.request;

import com.compay.msbanking.enums.AccountStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountRequest implements Serializable {

    private String iban;

    private String number;

    private CurrencyEnum currency;

    private BigDecimal balance;

    private AccountStatusEnum status;

    private Long customerId;

    public AccountRequest() {
    }

    public AccountRequest(String iban, String number, CurrencyEnum currency, BigDecimal balance,
                          AccountStatusEnum status, Long customerId) {
        this.iban = iban;
        this.number = number;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AccountStatusEnum status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
