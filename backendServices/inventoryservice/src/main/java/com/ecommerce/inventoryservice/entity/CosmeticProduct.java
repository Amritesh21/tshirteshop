package com.ecommerce.inventoryservice.entity;

public class CosmeticProduct extends Product {

    private String brand;

    private String manufacturedOn;

    private String useBy;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufacturedOn() {
        return manufacturedOn;
    }

    public void setManufacturedOn(String manufacturedOn) {
        this.manufacturedOn = manufacturedOn;
    }

    public String getUseBy() {
        return useBy;
    }

    public void setUseBy(String useBy) {
        this.useBy = useBy;
    }
}
