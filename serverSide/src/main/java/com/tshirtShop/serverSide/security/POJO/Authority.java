package com.tshirtShop.serverSide.security.POJO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Authority {

    @GeneratedValue
    private String auth_id;

    @Id
    private String authority_name;

    @ManyToMany(mappedBy = "authorities")
    private List<UserEntity> users = new ArrayList<>();

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getAuthority_name() {
        return authority_name;
    }

    public void setAuthority_name(String authority_name) {
        this.authority_name = authority_name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

}
