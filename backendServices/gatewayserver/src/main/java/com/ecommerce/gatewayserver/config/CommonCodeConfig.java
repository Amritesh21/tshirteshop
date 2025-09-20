package com.ecommerce.gatewayserver.config;

import com.ecommerce.commoncode.utils.JwtHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonCodeConfig {

    // com.ecommerce.commoncode.utils.JwtHelper jwtHelper = new com.ecommerce.commoncode.utils.JwtHelper();

    @Bean
    public JwtHelper getJwtHelperBean() {
        return new JwtHelper();
    }

}
