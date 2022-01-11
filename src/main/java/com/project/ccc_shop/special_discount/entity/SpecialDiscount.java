package com.project.ccc_shop.special_discount.entity;

import com.project.ccc_shop.product.entity.Category;

import java.sql.Timestamp;

public class SpecialDiscount {
    private int discountCode;
    private int venderId;
    private String venderName;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private Category category;
    private double discountRate;
    private String startDate;
    private String endDate;

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
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
        setStartDate();
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        setEndDate();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate() {
        if (this.startTime != null) {
            this.startDate = this.startTime.toString().split(" ")[0] + " " + this.startTime.toString().split(" ")[1].split(":")[0] + ":" + this.startTime.toString().split(" ")[1].split(":")[1];
        }
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate() {
        if (this.endTime != null) {
            this.endDate = this.endTime.toString().split(" ")[0] + " " + this.endTime.toString().split(" ")[1].split(":")[0] + ":" + this.endTime.toString().split(" ")[1].split(":")[1];
        }
    }
}
