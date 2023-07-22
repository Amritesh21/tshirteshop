package com.tshirtShop.serverSide.security.filters;

import com.tshirtShop.serverSide.security.config.APIWhiteListing;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthFilter extends OncePerRequestFilter {

    private APIWhiteListing apiWhiteListing;

    public JWTAuthFilter(APIWhiteListing apiWhiteListing) {
        this.apiWhiteListing = apiWhiteListing;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith(apiWhiteListing.getAPIAccess("authenticated"))) {
            String authToken = request.getHeader("Auth-Token");
            if (authToken != null) {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor("jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4".getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(authToken)
                        .getBody();
                String username = claims.get("username").toString();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null,
                        Arrays.stream(claims.get("authorities").toString().split(",")).map(x -> new SimpleGrantedAuthority(x)).collect(Collectors.toList())));
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(400);
                response.getWriter().write("Unauthorized access restricted");
                return;
            }
        } else if(request.getRequestURI().startsWith(apiWhiteListing.getAPIAccess("unauthenticated"))) {
            filterChain.doFilter(request, response);
        }

        return;
    }
}
