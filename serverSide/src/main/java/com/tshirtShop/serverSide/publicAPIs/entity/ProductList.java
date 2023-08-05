package com.tshirtShop.serverSide.publicAPIs.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductList {

    @Id
    @GeneratedValue
    private Long productId;
    private String username;
    private String productDescription;
    private String productCategory;

    @ElementCollection
    private List<String> colors;
    @ElementCollection
    private List<String> sizes;
    private Long productPrice;
    private Long totalStockPresent;

    @Lob
    private byte [] thumbNailImage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productList")
    private List<ProductImageList> productImageLists = new ArrayList<>();

    public ProductList() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getTotalStockPresent() {
        return totalStockPresent;
    }

    public void setTotalStockPresent(Long totalStockPresent) {
        this.totalStockPresent = totalStockPresent;
    }

    public void setProductImageLists(ProductImageList productImageList) {
        productImageLists.add(productImageList);
    }

    public List<ProductImageList> getProductImageLists() {
        return productImageLists;
    }

    public byte[] getThumbNailImage() {
        return thumbNailImage;
    }

    public void setThumbNailImage(byte[] thumbNailImage) {
        this.thumbNailImage = thumbNailImage;
    }
}
