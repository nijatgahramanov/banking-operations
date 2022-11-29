package com.compay.msbanking.entity;

import com.compay.msbanking.enums.AccountStatusEnum;
import com.compay.msbanking.enums.CurrencyEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="iban",length = 28,unique = true)
    private String iban;

    @Column(name="account_number",unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @Column(name="balance")
    private BigDecimal balance;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private AccountStatusEnum status;

    @Column(name="active")
    private Integer active=1;

    @CreationTimestamp
    @Column(name="created_date")
    private Date createdDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    public Account(){}

    public Account(String iban, String number, CurrencyEnum currency,
                   BigDecimal balance, AccountStatusEnum status,
                   Integer active, Date createdDate,
                   Customer customer, List<Card> cards) {
        this.iban = iban;
        this.number = number;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.active = active;
        this.createdDate = createdDate;
        this.customer = customer;
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", status=" + status +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", customer_id=" + customer +
                ", cards=" + cards +
                '}';
    }
}
