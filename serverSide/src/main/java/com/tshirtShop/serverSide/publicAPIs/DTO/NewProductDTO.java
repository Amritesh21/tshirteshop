package com.tshirtShop.serverSide.publicAPIs.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class NewProductDTO {

    private String userName;
    private Long productId;
    private String productCategory;
    private String productDescription;
    private Long productPrice;
    private List<String> colorsArray;
    private List<String> selectSizes;
    private String targetGender;
    private Long totalStock;

    public NewProductDTO() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public List<String> getColorsArray() {
        return colorsArray;
    }

    public void setColorsArray(List<String> colorsArray) {
        this.colorsArray = colorsArray;
    }

    public List<String> getSelectSizes() {
        return selectSizes;
    }

    public void setSelectSizes(List<String> selectSizes) {
        this.selectSizes = selectSizes;
    }

    public String getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(String targetGender) {
        this.targetGender = targetGender;
    }

    public Long getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Long totalStock) {
        this.totalStock = totalStock;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
