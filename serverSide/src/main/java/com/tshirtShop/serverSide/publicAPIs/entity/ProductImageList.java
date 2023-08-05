package com.tshirtShop.serverSide.publicAPIs.entity;

import javax.persistence.*;

@Entity
public class ProductImageList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;

    private String imageName;

    @Lob
    private byte[] imageFile;

    @ManyToOne
    private ProductList productList;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }


    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }
}
