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
    private Map<String, Integer> orderItems = new HashMap<>();   // Map<productName, quantity>
    private String orderDate;
    private String shippingDate;
    private String deliveryDate;

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
        setOrderDate();
    }

    public Timestamp getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Timestamp shippingTime) {
        this.shippingTime = shippingTime;
        setShippingDate();
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
        setDeliveryDate();
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

    public Map<String, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<String, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public void putOrderItem(String productName, int quantity) {
        if (orderItems.containsKey(productName)) {
            int oldQuantity = orderItems.get(productName);
            orderItems.put(productName, oldQuantity + quantity);
            return;
        }
        orderItems.put(productName, quantity);
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate() {
        this.orderDate = this.orderTime.toString().split(" ")[0] + " " + this.orderTime.toString().split(" ")[1].split(":")[0] + ":" + this.orderTime.toString().split(" ")[1].split(":")[1];
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate() {
        if (this.shippingTime != null) {
            this.shippingDate = this.shippingTime.toString().split(" ")[0] + " " + this.shippingTime.toString().split(" ")[1].split(":")[0] + ":" + this.shippingTime.toString().split(" ")[1].split(":")[1];
        }
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate() {
        if (this.deliveryTime != null) {
            this.deliveryDate = this.deliveryTime.toString().split(" ")[0] + " " + this.deliveryTime.toString().split(" ")[1].split(":")[0] + ":" + this.deliveryTime.toString().split(" ")[1].split(":")[1];
        }
    }
}
