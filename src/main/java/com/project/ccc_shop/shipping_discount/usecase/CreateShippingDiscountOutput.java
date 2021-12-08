package com.project.ccc_shop.shipping_discount.usecase;

import com.project.ccc_shop.common.Output;

public class CreateShippingDiscountOutput implements Output {
    private int discountCode;

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }
}
