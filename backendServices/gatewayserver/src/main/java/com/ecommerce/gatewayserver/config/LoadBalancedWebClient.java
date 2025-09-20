package com.ecommerce.gatewayserver.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LoadBalancedWebClient {

    @Bean
    @LoadBalanced
    public WebClient.Builder getRestTemplateBean() {
        return WebClient.builder();
    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

}
