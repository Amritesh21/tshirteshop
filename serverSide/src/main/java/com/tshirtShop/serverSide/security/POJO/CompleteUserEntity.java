package com.tshirtShop.serverSide.security.POJO;

import javax.persistence.*;

@Entity
public class CompleteUserEntity {

    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "completeUserEntity", cascade = CascadeType.ALL)
    private UserEntity userEntity;

    @Id
    @Column(nullable = false, unique = true)
    private String username;
    private long phoneNo;
    private long alternatePhoneNo;
    private String address;

    public CompleteUserEntity(){}

    public CompleteUserEntity(String username, long phoneNo, long alternatePhoneNo, String address) {
        this.username = username;
        this.phoneNo = phoneNo;
        this.alternatePhoneNo = alternatePhoneNo;
        this.address = address;
    }

    public CompleteUserEntity(String username, long phoneNo, long alternatePhoneNo, String address, UserEntity userEntity) {
        this.username = username;
        this.phoneNo = phoneNo;
        this.alternatePhoneNo = alternatePhoneNo;
        this.address = address;
        this.userEntity = userEntity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
