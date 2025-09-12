package com.ecommerce.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
        try {
            final Path productImagesPaths = Paths.get("productImages");
            if (!Files.exists(productImagesPaths)) {
                Files.createDirectories(productImagesPaths);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
