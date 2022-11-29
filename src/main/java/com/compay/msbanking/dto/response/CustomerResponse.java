package com.compay.msbanking.dto.response;

import com.compay.msbanking.enums.CustomerTypeEnum;

import java.io.Serializable;
import java.util.List;

public class CustomerResponse implements Serializable {

    private Long id;

    private String firstname;

    private String lastname;

    private String fatherName;

    private Integer age;

    private String address;

    private String fin;

    private CustomerTypeEnum customerType;

    private List<AccountResponse> accountResponse;

    public CustomerResponse(){

    }
    public CustomerResponse(Long id,String firstname, String lastname, String fatherName,
                           Integer age, String address, String fin,
                           CustomerTypeEnum customerType,List<AccountResponse> accountResponse) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.age = age;
        this.address = address;
        this.fin = fin;
        this.customerType = customerType;
        this.id=id;
        this.accountResponse=accountResponse;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getFin() {
        return fin;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public CustomerResponse setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CustomerResponse setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CustomerResponse setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public CustomerResponse setAge(Integer age) {
        this.age = age;
        return this;
    }

    public CustomerResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerResponse setFin(String fin) {
        this.fin = fin;
        return this;
    }

    public CustomerResponse setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
        return this;
    }

    public CustomerResponse setId(Long id){
        this.id= id;
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public List<AccountResponse> getAccountResponse() {
        return accountResponse;
    }

    public CustomerResponse setAccountResponse(List<AccountResponse> accountResponse) {
        this.accountResponse = accountResponse;
        return this;
    }
}
