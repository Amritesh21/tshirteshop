package com.tshirtShop.serverSide.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
public class EnvironmentConfig {

    @Bean
    public DataSource setDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter database URL: ");
        String dbURL = scanner.nextLine();
        dataSourceBuilder.url(dbURL);
        System.out.println("Please enter database username: ");
        String dbUserName = scanner.nextLine();
        dataSourceBuilder.username(dbUserName);
        System.out.println("Please enter database password: ");
        String dbPassword = scanner.nextLine();
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }

}
