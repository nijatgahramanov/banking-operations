package com.compay.msbanking.entity;

import com.compay.msbanking.enums.CustomerTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;

    @Column(name="father_name")
    private String fatherName;

    @Column(name="age")
    private Integer age;

    @Column(name="address")
    private String address;

    @Column(name="fin",unique = true)
    private String fin;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="customer_type")
    private CustomerTypeEnum customerType;

    @CreationTimestamp
    @Column(name="create_date")
    private Date createDate;

    @Column(name="active")
    private Integer active=1;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Account> accounts;

    public Customer(){}
    public Customer(Long id,String firstname, String lastname, String fatherName, Integer age,
                    String address, String fin, CustomerTypeEnum customerType, Date createDate,
                    Integer active, List<Account> accounts) {
        this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.age = age;
        this.address = address;
        this.fin = fin;
        this.customerType = customerType;
        this.createDate = createDate;
        this.active = active;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
