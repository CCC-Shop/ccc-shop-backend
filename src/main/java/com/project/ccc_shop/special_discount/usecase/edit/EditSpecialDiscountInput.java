package com.project.ccc_shop.special_discount.usecase.edit;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;

import java.sql.Timestamp;
import java.util.Map;

public class EditSpecialDiscountInput implements Input {
    private int discountCode;
    private int venderId;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private Category category;
    private double discountRate;

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) { this.venderId = venderId; }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate){
        this.discountRate = discountRate;
    }

}
