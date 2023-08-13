package com.tshirtShop.serverSide.publicAPIs.DTO;

import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrderCart;
import com.tshirtShop.serverSide.publicAPIs.entity.PublicImage;

public class CartProductDTO {

    private NewProductDTO newProductDTO;

    private CustomerOrderCart customerOrderCart;

    public CartProductDTO() {}

    public CartProductDTO(NewProductDTO newProductDTO, CustomerOrderCart customerOrderCart) {
        this.newProductDTO = newProductDTO;
        this.customerOrderCart = customerOrderCart;
    }

    public NewProductDTO getNewProductDTO() {
        return newProductDTO;
    }

    public void setNewProductDTO(NewProductDTO newProductDTO) {
        this.newProductDTO = newProductDTO;
    }

    public CustomerOrderCart getCustomerOrderCart() {
        return customerOrderCart;
    }

    public void setCustomerOrderCart(CustomerOrderCart customerOrderCart) {
        this.customerOrderCart = customerOrderCart;
    }
}
