package com.tshirtShop.serverSide.security.repository;

import com.tshirtShop.serverSide.security.POJO.Authority;
import com.tshirtShop.serverSide.security.POJO.CompleteUserEntity;
import com.tshirtShop.serverSide.security.POJO.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserEntityService {

    @Autowired
    EntityManager entityManager;

    public boolean addUser(UserEntity userEntity) {
        try {
            entityManager.persist(userEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addAuthority(Authority authority) {
        try {
            entityManager.persist(authority);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getAllAuthorities() {
        try{
            Query query = entityManager.createNativeQuery("Select * from authority");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public UserEntity getUserByUsername(String username) {
        return entityManager.find(UserEntity.class, username);
    }

    public CompleteUserEntity getCompleteUserByUsername(String username) {
        return entityManager.find(CompleteUserEntity.class, username);
    }

    public boolean setCompleteUserDetails(CompleteUserEntity completeUserEntity) {
        try{
            entityManager.persist(completeUserEntity);
            return  true;
        } catch(Exception e) {
            return false;
        }
    }

}
