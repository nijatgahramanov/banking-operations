package com.compay.msbanking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customerTypes")
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="active")
    private Integer active;

    @Column(name="create_date")
    private Date createDate;

    public CustomerType(){

    }

    public CustomerType(String name, Integer active, Date createDate) {
        this.name = name;
        this.active = active;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
