package com.project.ccc_shop.order.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private int customerId;
    private int shippingFee;
    private String recipientName;
    private String shippingAddress;
    private Status status;
    private Payment paymentMethod;
    private String creditCardId;
    private Timestamp orderTime;
    private Timestamp shippingTime;
    private Timestamp deliveryTime;
    private int seasoningDiscountCode;
    private int shippingDiscountCode;
    private int totalPrice;
    private Map<Integer, Integer> orderItems = new HashMap<>();   // Map<productId, quantity>

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(int shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Timestamp shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getSeasoningDiscountCode() {
        return seasoningDiscountCode;
    }

    public void setSeasoningDiscountCode(int seasoningDiscountCode) {
        this.seasoningDiscountCode = seasoningDiscountCode;
    }

    public int getShippingDiscountCode() {
        return shippingDiscountCode;
    }

    public void setShippingDiscountCode(int shippingDiscountCode) {
        this.shippingDiscountCode = shippingDiscountCode;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<Integer, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(int productId, int quantity) {
        if (orderItems.containsKey(productId)) {
            int oldQuantity = orderItems.get(productId);
            orderItems.put(productId, oldQuantity + quantity);
            return;
        }
        orderItems.put(productId, quantity);
    }
}
