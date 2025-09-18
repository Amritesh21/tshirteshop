package com.ecommerce.authservice.entity;

import jakarta.persistence.*;

@Entity
public class CompleteUserEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;

    private long phoneNo;
    private long alternatePhoneNo;
    private String address;
    @OneToOne(mappedBy = "completeUserEntity")
    UserEntity userEntity;

    public CompleteUserEntity(){}

    public Long getId() {
        return id;
    }

    public void setUsername(Long id) {
        this.id = id;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public long getAlternatePhoneNo() {
        return alternatePhoneNo;
    }

    public void setAlternatePhoneNo(long alternatePhoneNo) {
        this.alternatePhoneNo = alternatePhoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() { return userEntity; }
}
