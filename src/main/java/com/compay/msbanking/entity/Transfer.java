package com.compay.msbanking.entity;

import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.enums.TransferStatusEnum;
import com.compay.msbanking.enums.TransferTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="transfer")
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "debitor_card_id")
    private Long debitorCardId;

    @Column(name = "creditor_card_id")
    private Long creditorCardId;

    @Column(name = "debitor_account_id")
    private Long debitorAccountId;

    @Column(name = "creditor_account_id")
    private Long creditorAccountId;

    @Column(name = "debitor_amount")
    private BigDecimal debitorAmount;

    @Column(name = "debitor_currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum debitorCurrency;

    @Column(name = "creditor_amount")
    private BigDecimal creditorAmount;

    @Column(name = "creditor_currency",length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum creditorCurrency;

    @Column(name = "transfer_type")
    @Enumerated(EnumType.ORDINAL)
    private TransferTypeEnum transferType;


    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private TransferStatusEnum status;


    @Column(name = "active")
    private Integer active;

    @Column(name = "create_date")
    private Date createDate;

    public Transfer(){}

    public Transfer(Long debitorCardId, Long creditorCardId, Long debitorAccountId, Long creditorAccountId,
                    BigDecimal debitorAmount, CurrencyEnum debitorCurrency, BigDecimal creditorAmount,
                    CurrencyEnum creditorCurrency, TransferTypeEnum transferType, TransferStatusEnum status,
                    Integer active, Date createDate) {
        this.debitorCardId = debitorCardId;
        this.creditorCardId = creditorCardId;
        this.debitorAccountId = debitorAccountId;
        this.creditorAccountId = creditorAccountId;
        this.debitorAmount = debitorAmount;
        this.debitorCurrency = debitorCurrency;
        this.creditorAmount = creditorAmount;
        this.creditorCurrency = creditorCurrency;
        this.transferType = transferType;
        this.status = status;
        this.active = active;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public Transfer setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDebitorCardId() {
        return debitorCardId;
    }

    public Transfer setDebitorCardId(Long debitorCardId) {
        this.debitorCardId = debitorCardId;
        return this;
    }

    public Long getCreditorCardId() {
        return creditorCardId;
    }

    public Transfer setCreditorCardId(Long creditorCardId) {
        this.creditorCardId = creditorCardId;
        return this;
    }

    public Long getDebitorAccountId() {
        return debitorAccountId;
    }

    public Transfer setDebitorAccountId(Long debitorAccountId) {
        this.debitorAccountId = debitorAccountId;
        return this;
    }

    public Long getCreditorAccountId() {
        return creditorAccountId;
    }

    public Transfer setCreditorAccountId(Long creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
        return this;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public Transfer setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
        return this;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public Transfer setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
        return this;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public Transfer setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
        return this;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public Transfer setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
        return this;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public Transfer setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
        return this;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public Transfer setStatus(TransferStatusEnum status) {
        this.status = status;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public Transfer setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Transfer setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
}
