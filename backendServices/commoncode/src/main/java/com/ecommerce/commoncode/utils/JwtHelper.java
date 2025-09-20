package com.ecommerce.commoncode.utils;

import com.ecommerce.commoncode.constants.AuthorityTypes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtHelper {

    private final SecretKey secretKey = Keys.hmacShaKeyFor("aquickbrownfoxjumpsrightoverthelazydog".getBytes());

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


}
