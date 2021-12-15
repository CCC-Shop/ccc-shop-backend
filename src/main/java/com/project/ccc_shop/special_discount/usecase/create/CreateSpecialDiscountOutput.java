package com.project.ccc_shop.special_discount.usecase.create;

import com.project.ccc_shop.common.Output;

public class CreateSpecialDiscountOutput implements Output {
    private int discountCode;

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }
}
