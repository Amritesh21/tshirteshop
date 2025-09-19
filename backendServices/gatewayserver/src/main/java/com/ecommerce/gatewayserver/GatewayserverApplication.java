package com.ecommerce.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes().
                route(x -> x.path("/inventory/**")
                        .filters( f -> f.rewritePath("/inventory/(?<segment>.*)","/${segment}"))
                        .uri("lb://INVENTORYSERVICE"))
                .route(x -> x.path("/order/**")
                        .filters(f -> f.rewritePath("/order/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ORDERSERVICE")
                )
                .route(x -> x.path("/auth/**")
                        .filters(f -> f.rewritePath("/auth/(?<segment>.*)", "/${segment}"))
                        .uri("lb://AUTHSERVICE")
                )
                .build();

    }

}
