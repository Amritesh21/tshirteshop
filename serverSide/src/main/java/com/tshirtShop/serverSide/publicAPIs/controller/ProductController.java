package com.tshirtShop.serverSide.publicAPIs.controller;

import com.tshirtShop.serverSide.publicAPIs.DTO.NewProductDTO;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductImageList;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import com.tshirtShop.serverSide.publicAPIs.repository.ProductRepo;
import com.tshirtShop.serverSide.publicAPIs.repository.UploadPublicImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
public class ProductController {

    @Autowired
    UploadPublicImageRepo uploadPublicImageRepo;

    @Autowired
    ProductRepo productRepo;

    @PostMapping("/api/public/add/new/product")
    public ResponseEntity<String> saveNewProduct(@RequestParam("userName") String userName, @RequestParam("productCategory") String productCategory,
                                                 @RequestParam("productDescription") String productDescription, @RequestParam("productPrice") Long productPrice,
                                                 @RequestParam("colorsArray") List<String> colorsArray,  @RequestParam("selectSizes") List<String> selectSizes,
                                                 @RequestParam("targetGender") String targetGender, @RequestParam("totalStock") Long totalStock,
                                                 @RequestParam("productImageArray") List<MultipartFile> productImages, @RequestParam("productId") Long productId) {
        ProductList productList = new ProductList();
        productList.setProductId(productId);
        productList.setProductCategory(productCategory);
        productList.setProductDescription(productDescription);
        productList.setProductPrice(productPrice);
        productList.setUsername(userName);
        productList.setColors(colorsArray);
        productList.setSizes(selectSizes);
        productList.setTotalStockPresent(totalStock);
        productImages.stream().map(x -> {
            try {
                ProductImageList productImageList = new ProductImageList();
                productImageList.setImageFile(x.getBytes());
                productImageList.setImageName(x.getOriginalFilename());
                productImageList.setProductList(productList);
                return productImageList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).forEach(obj -> productList.setProductImageLists(obj));
        productList.setThumbNailImage(productList.getProductImageLists().get(0).getImageFile());
        if (productId != null && productId != 0) {
            uploadPublicImageRepo.clearExistingProductImage(productId);
        }
        productRepo.save(productList);
        return ResponseEntity.ok().body("Product added successfully");
    }

    @GetMapping(value = "/api/public/seller/get/all/products/meta/{username}")
    public List<NewProductDTO> getAllMyUploadedProducts(@PathVariable String username) {
        List<ProductList> productLists = uploadPublicImageRepo.fetchAllProductImage(username);
        List<NewProductDTO> getProductDTO = productLists.stream().map(p -> {
            NewProductDTO newProductDTO = new NewProductDTO();
            newProductDTO.setProductId(p.getProductId());
            newProductDTO.setProductCategory(p.getProductCategory());
            newProductDTO.setProductDescription(p.getProductDescription());
            newProductDTO.setProductPrice(p.getProductPrice());
            newProductDTO.setSelectSizes(p.getSizes());
            newProductDTO.setColorsArray(p.getColors());
            return newProductDTO;
        }).collect(Collectors.toList());
        return getProductDTO;
    }

    @GetMapping(value = "/api/public/seller/get/product/meta/{productId}")
    public NewProductDTO getAllMyUploadedProducts(@PathVariable Long productId) {
        ProductList p = uploadPublicImageRepo.fetchProductDetails(productId);
            NewProductDTO newProductDTO = new NewProductDTO();
            newProductDTO.setProductId(p.getProductId());
            newProductDTO.setProductCategory(p.getProductCategory());
            newProductDTO.setProductDescription(p.getProductDescription());
            newProductDTO.setProductPrice(p.getProductPrice());
            newProductDTO.setSelectSizes(p.getSizes());
            newProductDTO.setColorsArray(p.getColors());
            newProductDTO.setTotalStock(p.getTotalStockPresent());
            return newProductDTO;
    }

    @GetMapping(value = "/api/public/seller/get/thumbNail/{username}/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getMyProductsThumbNail(@PathVariable String username, @PathVariable Long productId) {
        return uploadPublicImageRepo.fetchProductThumbNail(username, productId);
    }

    @GetMapping(value = "/api/public/seller/get/product/images/meta/{productId}")
    public List<Long> getProductImageMeta(@PathVariable Long productId) {
        List<ProductImageList> productImageList = uploadPublicImageRepo.fetchProductImageMeta(productId);
        List<Long> productImageIdMeta = productImageList.stream().map(x -> x.getImageId()).collect(Collectors.toList());
        return productImageIdMeta;
    }

    @GetMapping(value = "/api/public/seller/get/product/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProductImage(@PathVariable Long imageId) {
        ProductImageList productImageList = uploadPublicImageRepo.fetchProductImage(imageId);
        return productImageList.getImageFile();
    }

}
