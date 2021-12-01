package com.project.ccc_shop.seasonings_discount.usecase;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.product.entity.Category;

import java.sql.Timestamp;

public class CreateSeasoningsDiscountInput implements Input {
//    private String discountCode;
    private int venderId;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private double discountRate;
//    private int targetPrice;

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

//{
//    "venderId": 456,
//    "policyDescription": "policy description post",
//    "startTime": "2021-11-22T13:47:58.212+00:00",
//    "endTime": "2021-11-24T13:47:58.212+00:00",
//    "discountRate": 0.5
//}
}
