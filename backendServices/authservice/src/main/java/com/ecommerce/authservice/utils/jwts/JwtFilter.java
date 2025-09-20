package com.ecommerce.authservice.utils.jwts;

import com.ecommerce.authservice.service.impl.EStoreUserDetailsManager;
import com.ecommerce.authservice.service.impl.EStoreUserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Executing filter");
        String bearerToken = request.getHeader("Bearer-Token");
        if (bearerToken != null) {
            Claims jwtParsedClaims = jwtHelper.parseJwt(bearerToken);
            String username = jwtHelper.getUsername(jwtParsedClaims);
            Boolean isExpired = jwtHelper.isTokenExpired(jwtParsedClaims);
            List<GrantedAuthority> authorities = jwtHelper.getAuthorities(jwtParsedClaims);
            if (!ObjectUtils.isEmpty(username) && !isExpired) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(bearerToken, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }
        filterChain.doFilter(request, response);
    }
}
