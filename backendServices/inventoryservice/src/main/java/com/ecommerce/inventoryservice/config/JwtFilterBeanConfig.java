package com.ecommerce.inventoryservice.config;

import com.ecommerce.commoncode.utils.JwtHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterBeanConfig {

    @Bean
    public JwtHelper getJwtFilterBean() {
        return new JwtHelper();
    }


}
