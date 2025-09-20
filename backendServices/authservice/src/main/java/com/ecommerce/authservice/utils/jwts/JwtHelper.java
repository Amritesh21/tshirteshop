package com.ecommerce.authservice.utils.jwts;



import com.ecommerce.authservice.constants.AuthorityTypes;
import com.ecommerce.authservice.entity.Authority;
import com.ecommerce.authservice.entity.LoginTokenEntity;
import com.ecommerce.authservice.repository.EStoreLoginTokenRepo;
import com.ecommerce.authservice.repository.EStoreUserDetailsRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtHelper {

    private final EStoreLoginTokenRepo eStoreLoginTokenRepo;
    private final EStoreUserDetailsRepo eStoreUserDetailsRepo;

//    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    private final SecretKey secretKey = Keys.hmacShaKeyFor("aquickbrownfoxjumpsrightoverthelazydog".getBytes());

    @Autowired
    public JwtHelper(EStoreLoginTokenRepo eStoreLoginTokenRepo, EStoreUserDetailsRepo eStoreUserDetailsRepo) {
        this.eStoreLoginTokenRepo = eStoreLoginTokenRepo;
        this.eStoreUserDetailsRepo = eStoreUserDetailsRepo;
    }

    public Claims parseJwt(String jwt) {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt);
        Claims claims = claimsJws.getPayload();
        if (claims.get("issuedBy").equals("ecommerceStore")) {
            return claims;
        }
        return null;
    }

    public String getUsername(Claims claims) {
        if (ObjectUtils.isEmpty(claims)) {
            return null;
        } else {
            return (String) claims.get("username");
        }
    }

    public Boolean isTokenExpired(Claims claims) {
        if (ObjectUtils.isEmpty((claims))) {
            return true;
        } else {
            Date expDate = claims.getExpiration();
            return expDate.before(new Date());
        }
    }

    public List<GrantedAuthority> getAuthorities(Claims claims) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (AuthorityTypes at : AuthorityTypes.values()) {
            if (claims.get(at.toString()) != null && claims.get(at.toString()).equals("true")) {
                list.add(new SimpleGrantedAuthority(at.toString()));
            }
        }
        return list;
    }


    public String generateNewAuthToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Map<String, String> claims = new HashMap<>();
        claims.put("issuedBy", "ecommerceStore");
        claims.put("username", securityContext.getAuthentication().getName());
        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+60*60*1000)).compact().toString();

        LoginTokenEntity loginTokenEntity = new LoginTokenEntity();
        loginTokenEntity.setToken(jwt);
        loginTokenEntity.setUsername(eStoreUserDetailsRepo.findById(securityContext.getAuthentication().getName()).get());
        eStoreLoginTokenRepo.save(loginTokenEntity);
        return jwt;
    }

    public <T> String generateBearerToken(String username, List<? extends GrantedAuthority> authorityList) {
        Map<String, String> claims = new HashMap<>();
        claims.put("issuedBy", "ecommerceStore");
        claims.put("username", username);
        authorityList.stream().map(GrantedAuthority::getAuthority)
                .forEach(x -> {
                    if (x.equals(AuthorityTypes.ADMIN.toString()) || x.equals(AuthorityTypes.SELLER.toString()) || x.equals(AuthorityTypes.BUYER.toString())) {
                        claims.put(x.toString(), "true");
                    }
                });
        return Jwts.builder().claims(claims).signWith(secretKey)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60*60*1000)).compact().toString();
    }

}
