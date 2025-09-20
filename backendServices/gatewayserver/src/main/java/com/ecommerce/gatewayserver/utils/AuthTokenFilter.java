package com.ecommerce.gatewayserver.utils;

import com.ecommerce.commoncode.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthTokenFilter implements WebFilter {

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    JwtHelper jwtHelper;

    private UsernamePasswordAuthenticationToken setupSecurityContext(String token) {
        Claims claims = jwtHelper.parseJwt(token);
        String username = jwtHelper.getUsername(claims);
        List<GrantedAuthority> authorities = jwtHelper.getAuthorities(claims);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        List<String> authTokens = exchange.getRequest().getHeaders().get("Auth-Token");
        if (authTokens != null && !authTokens.isEmpty()) {
            String authToken = authTokens.getFirst();
            return webClientBuilder.build().get().uri("lb://AUTHSERVICE/api/verify/token")
                    .header("Auth-Token", authToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .flatMap(x -> {
                        System.out.println(x);
                        if (!ObjectUtils.isEmpty(x)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = setupSecurityContext(x);

                            exchange.getRequest().getHeaders().add("Bearer-Token", x);
                            exchange.getRequest().getHeaders().remove("Auth-Token");
                           return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(usernamePasswordAuthenticationToken));
                        } else {
                            return exchange.getResponse().setComplete();
                        }
                    });
        }
        return chain.filter(exchange);
    }
}
