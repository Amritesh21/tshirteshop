package com.tshirtShop.serverSide;

import com.tshirtShop.serverSide.security.config.EnvironmentConfig;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class ServerSideApplication {
	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(ServerSideApplication.class, args);
//		Scanner scanner = new Scanner(System.in);
//		String enteredURL = scanner.nextLine();
//		String dbURLVar = System.getenv("DB_URL");
//		dbURLVar = enteredURL;
//		System.out.println(System.getenv("DB_URL"));
		SpringApplication.run(ServerSideApplication.class, args);
	}

}
