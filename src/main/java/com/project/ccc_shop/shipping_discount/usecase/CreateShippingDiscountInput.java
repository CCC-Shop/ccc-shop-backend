package com.project.ccc_shop.shipping_discount.usecase;

import com.project.ccc_shop.common.Input;

import java.sql.Timestamp;

public class CreateShippingDiscountInput implements Input {
//    private String discountCode;
    private int orderId;
    private int userId;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private int targetPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(int targetPrice) {
        this.targetPrice = targetPrice;
    }

//{
//    "orderId": 456,
//    "userId": 456,
//    "policyDescription": "policy description post",
//    "startTime": "2021-11-22T13:47:58.212+00:00",
//    "endTime": "2021-11-24T13:47:58.212+00:00",
//    "targetPrice": 100
//}
}
