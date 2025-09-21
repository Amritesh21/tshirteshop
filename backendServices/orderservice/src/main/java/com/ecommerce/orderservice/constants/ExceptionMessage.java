package com.ecommerce.orderservice.constants;

public enum ExceptionMessage {

    INVALID_ORDER("Entered order is invalid"),
    UNAUTHORIZED_ATTEMPT("You can't update this order/cart item. Action will be reported"),
    INVALID_CART_ACCESS("Entered cart item is invalid");

    String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }


}
