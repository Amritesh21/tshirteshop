package com.ecommerce.authservice.utils;

import com.ecommerce.authservice.constants.AuthorityTypes;
import com.ecommerce.authservice.entity.Authority;
import com.ecommerce.authservice.repository.EStoreUserAuthorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedingAuthorityUtil implements CommandLineRunner {

    private final EStoreUserAuthorityRepo eStoreUserAuthorityRepo;

    @Autowired
    SeedingAuthorityUtil(EStoreUserAuthorityRepo eStoreUserAuthorityRepo) {
        this.eStoreUserAuthorityRepo = eStoreUserAuthorityRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Authority seller = new Authority();
        seller.setAuthority_name(AuthorityTypes.SELLER.toString());
        Authority buyer = new Authority();
        buyer.setAuthority_name(AuthorityTypes.BUYER.toString());
        Authority admin = new Authority();
        admin.setAuthority_name(AuthorityTypes.ADMIN.toString());

        eStoreUserAuthorityRepo.save(seller);
        eStoreUserAuthorityRepo.save(buyer);
        eStoreUserAuthorityRepo.save(admin);
    }
}
