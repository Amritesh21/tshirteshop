package com.ecommerce.inventoryservice.utils;

import com.ecommerce.inventoryservice.dto.ProductImageDTO;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
public class ProductImageUtil {

    public synchronized boolean checkIfAllImagesAreUploaded(ProductImageDTO productImageDTO, int totalImagesCount) {
        File folder = new File("productImages/"+productImageDTO.getProductId());
        File[] files = folder.listFiles();
        return files != null && (Arrays.stream(files).map(File::isFile).count() == totalImagesCount);
    }

}
