package com.tshirtShop.serverSide.publicAPIs.POJO;

import java.util.Date;

public class SellerOrderInsights {

    Date orderDate;

    Long totalCost;

    public SellerOrderInsights() {}
    public SellerOrderInsights(Date orderDate, Long totalCost) {
        this.orderDate = orderDate;
        this.totalCost = totalCost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }
}
