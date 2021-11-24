package com.project.ccc_shop.discount.usecase;

import com.project.ccc_shop.common.Output;

public class CreateDiscountOutput implements Output {
    private String discountCode;

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
