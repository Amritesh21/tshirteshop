package com.tshirtShop.serverSide.publicAPIs.DTO;

public class GetOrderDTO {

        Long productId;

        String size;

        Long quantity;


        String color;

        String paymentMethod;

        String address;

        Long phno;

        String thumbNail;

        Long totalCost;

        String orderStatus;

        String productCategory;

        Long orderId;

        public GetOrderDTO() {
        }

        public GetOrderDTO(Long productId, String size, Long quantity, String color, String paymentMethod, String address, Long phno) {
            this.productId = productId;
            this.size = size;
            this.quantity = quantity;
            this.color = color;
            this.paymentMethod = paymentMethod;
            this.address = address;
            this.phno = phno;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Long getPhno() {
            return phno;
        }

        public void setPhno(Long phno) {
            this.phno = phno;
        }

        public String getThumbNail() {
            return thumbNail;
        }

        public void setThumbNail(String thumbNail) {
            this.thumbNail = thumbNail;
        }

        public Long getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(Long totalCost) {
            this.totalCost = totalCost;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
        }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
