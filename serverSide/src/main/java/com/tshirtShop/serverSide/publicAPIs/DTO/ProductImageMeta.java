package com.tshirtShop.serverSide.publicAPIs.DTO;

public class ProductImageMeta {

    Long imageId;

    Long productId;

    public ProductImageMeta() {}

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
