package com.tshirtShop.serverSide.publicAPIs.controller;

import com.tshirtShop.serverSide.publicAPIs.DTO.NewProductDTO;
import com.tshirtShop.serverSide.publicAPIs.DTO.PlaceOrderDTO;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductImageList;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import com.tshirtShop.serverSide.publicAPIs.repository.ProductRepo;
import com.tshirtShop.serverSide.publicAPIs.repository.UploadPublicImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
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

    @PostMapping("/api/auth/seller/add/new/product")
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
        productList.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
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

    @GetMapping(value = "/api/auth/seller/get/all/products/meta")
    public List<NewProductDTO> getAllMyUploadedProducts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
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

    @GetMapping(value = "/api/public/get/product/meta/{productId}")
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

//    @GetMapping(value = "/api/auth/seller/get/thumbNail/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getMyProductsThumbNailByUsername(@PathVariable Long productId) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return uploadPublicImageRepo.fetchProductThumbNail(username, productId);
//    }

    @GetMapping(value = "/api/public/get/product/images/meta/{productId}")
    public List<Long> getProductImageMeta(@PathVariable Long productId) {
        List<ProductImageList> productImageList = uploadPublicImageRepo.fetchProductImageMeta(productId);
        List<Long> productImageIdMeta = productImageList.stream().map(x -> x.getImageId()).collect(Collectors.toList());
        return productImageIdMeta;
    }

    @GetMapping(value = "/api/public/get/product/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProductImage(@PathVariable Long imageId) {
        ProductImageList productImageList = uploadPublicImageRepo.fetchProductImage(imageId);
        return productImageList.getImageFile();
    }

    @GetMapping(value = "/api/public/get/products/meta")
    public List<NewProductDTO> getAllProducts(@RequestParam int startPosition, @RequestParam int pageSize) {
        List<ProductList> productLists = uploadPublicImageRepo.getAllProducts(startPosition, pageSize);
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

    @GetMapping(value = "/api/public/get/products/count")
    public Long getTotalProductsCount() {
        return uploadPublicImageRepo.getProductsCount();
    }

    @GetMapping(value = "/api/public/get/thumbNail/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getMyProductsThumbNail(@PathVariable Long productId) {
        return uploadPublicImageRepo.fetchPublicProductThumbNail(productId);
    }


}
