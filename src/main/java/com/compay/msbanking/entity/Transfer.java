package com.compay.msbanking.entity;

import com.compay.msbanking.enums.CurrencyEnum;
import com.compay.msbanking.enums.TransferStatusEnum;
import com.compay.msbanking.enums.TransferTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="transfer")
public class Transfer {

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDebitorCardId() {
        return debitorCardId;
    }

    public void setDebitorCardId(Long debitorCardId) {
        this.debitorCardId = debitorCardId;
    }

    public Long getCreditorCardId() {
        return creditorCardId;
    }

    public void setCreditorCardId(Long creditorCardID) {
        this.creditorCardId = creditorCardID;
    }

    public Long getDebitorAccountId() {
        return debitorAccountId;
    }

    public void setDebitorAccountId(Long debitorAccountID) {
        this.debitorAccountId = debitorAccountID;
    }

    public Long getCreditorAccountId() {
        return creditorAccountId;
    }

    public void setCreditorAccountId(Long creditorAccountID) {
        this.creditorAccountId = creditorAccountID;
    }

    public BigDecimal getDebitorAmount() {
        return debitorAmount;
    }

    public void setDebitorAmount(BigDecimal debitorAmount) {
        this.debitorAmount = debitorAmount;
    }

    public CurrencyEnum getDebitorCurrency() {
        return debitorCurrency;
    }

    public void setDebitorCurrency(CurrencyEnum debitorCurrency) {
        this.debitorCurrency = debitorCurrency;
    }

    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    public void setCreditorAmount(BigDecimal creditorAmount) {
        this.creditorAmount = creditorAmount;
    }

    public CurrencyEnum getCreditorCurrency() {
        return creditorCurrency;
    }

    public void setCreditorCurrency(CurrencyEnum creditorCurrency) {
        this.creditorCurrency = creditorCurrency;
    }

    public TransferTypeEnum getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferTypeEnum transferType) {
        this.transferType = transferType;
    }

    public TransferStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransferStatusEnum status) {
        this.status = status;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
