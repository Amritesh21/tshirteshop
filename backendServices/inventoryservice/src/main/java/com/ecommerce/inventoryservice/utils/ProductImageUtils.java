package com.ecommerce.inventoryservice.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class ProductImageUtils {

    public boolean isImageAlreadyPresentForTheProduct(String productId, String imageName) {
        File directory = new File("productImages/"+productId);
        File[] files = directory.listFiles();
        if (files == null) {
            return true;
        }
        return Arrays.stream(files).filter(File::isFile).map(File::getName).filter(x -> x.equals(imageName)).findAny().isEmpty();
    }

}
