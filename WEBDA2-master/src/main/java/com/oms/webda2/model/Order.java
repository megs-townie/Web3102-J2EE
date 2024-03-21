package com.oms.webda2.model;

public class Order {
    private int orderId;
    private int productId;
    private int userId;

    public Order() { }

    public Order(int orderId, int productId, int userId) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
