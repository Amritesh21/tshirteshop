package com.ecommerce.inventoryservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailsDTO {

    String productName;

    String description;

    String category;

    List<String> colors;

    Long price;

    Long stock;

}
