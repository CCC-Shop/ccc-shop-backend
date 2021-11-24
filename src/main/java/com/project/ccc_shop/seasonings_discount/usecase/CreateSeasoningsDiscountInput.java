package com.project.ccc_shop.seasonings_discount.usecase;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.product.entity.Category;

import java.sql.Timestamp;

public class CreateSeasoningsDiscountInput implements Input {
//    private String discountCode;
    private int orderId;
    private int venderId;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private double discountRate;
//    private int targetPrice;
//    private Category category;

//    public String getDiscountCode() {
//        return discountCode;
//    }
//
//    public void setDiscountCode(String discountCode) {
//        this.discountCode = discountCode;
//    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

//    public int getTargetPrice() {
//        return targetPrice;
//    }
//
//    public void setTargetPrice(int targetPrice) {
//        this.targetPrice = targetPrice;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
