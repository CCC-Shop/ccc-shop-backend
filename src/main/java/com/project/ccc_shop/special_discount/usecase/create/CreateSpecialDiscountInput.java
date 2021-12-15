package com.project.ccc_shop.special_discount.usecase.create;

import com.project.ccc_shop.common.Input;

import java.sql.Timestamp;

public class CreateSpecialDiscountInput implements Input {
    private int productId;
    private int venderId;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private String category;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//{
//    "productId": 123,
//    "venderId": 456,
//    "policyDescription": "policy description post bb",
//    "startTime": "2021-11-22T13:47:58.212+00:00",
//    "endTime": "2021-11-24T13:47:58.212+00:00",
//    "category": "ipad"
//}
}
