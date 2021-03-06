package com.project.ccc_shop.order.usecase.create;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class CreateOrderInput implements Input {
    private int customerId;
    private int shippingFee;
    private String recipientName;
    private String shippingAddress;
    private Status status;
    private Payment payment;
    private String creditCardId;
    private Timestamp orderTime;
    private int seasoningDiscountCode;
    private int shippingDiscountCode;
    private int totalPrice;
    private Map<Integer, Integer> orderItems = new HashMap<>();

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
        return payment;
    }

    public void setPaymentMethod(Payment payment) {
        this.payment = payment;
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

    public Map<Integer, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<Integer, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
