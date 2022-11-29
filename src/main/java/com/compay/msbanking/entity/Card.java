package com.compay.msbanking.entity;

import com.compay.msbanking.enums.CardStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="card_number",unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    private CurrencyEnum currency;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private CardStatusEnum status;

    @Column(name="balance")
    private BigDecimal balance;

    @Column(name="active")
    private Integer active=1;

    @CreationTimestamp
    @Column(name="create_date")
    private Date createDate;

    @Column(name="expire_date")
    private Date expireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Card(){}

    public Card(String number, CurrencyEnum currency, CardStatusEnum status, BigDecimal balance,
                Integer active, Date createDate, Date expireDate, Account account) {
        this.number = number;
        this.currency = currency;
        this.status = status;
        this.balance = balance;
        this.active = active;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CardStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CardStatusEnum status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", status=" + status +
                ", balance=" + balance +
                ", active=" + active +
                ", createDate=" + createDate +
                ", expireDate=" + expireDate +
                ", account=" + account +
                '}';
    }
}
