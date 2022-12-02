package com.compay.msbanking.dto.request;


import com.compay.msbanking.enums.CustomerTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class CustomerRequest implements Serializable {


    private String firstname;

    private String lastname;

    private String fatherName;

    private Integer age;

    private String address;

    private String fin;

    private CustomerTypeEnum customerType;

    public CustomerRequest(String firstname, String lastname, String fatherName,
                           Integer age, String address, String fin,
                           CustomerTypeEnum customerType) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.age = age;
        this.address = address;
        this.fin = fin;
        this.customerType = customerType;
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


}
