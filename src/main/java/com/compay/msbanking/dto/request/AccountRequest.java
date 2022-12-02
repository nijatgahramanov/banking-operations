package com.compay.msbanking.dto.request;

import com.compay.msbanking.enums.AccountStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountRequest implements Serializable {



    private CurrencyEnum currency;

    private BigDecimal balance;

    private Long customerId;

    private AccountStatusEnum status;

    public AccountStatusEnum getStatus() {
        return status;
    }

    public AccountRequest setStatus(AccountStatusEnum status) {
        this.status = status;
        return this;
    }

    public AccountRequest() {
    }

    public AccountRequest(CurrencyEnum currency, BigDecimal balance,
                          Long customerId, AccountStatusEnum status) {

        this.currency = currency;
        this.balance = balance;
        this.customerId = customerId;
        this.status = status;
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




    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
