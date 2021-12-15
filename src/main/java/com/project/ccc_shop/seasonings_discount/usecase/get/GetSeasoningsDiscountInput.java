package com.project.ccc_shop.seasonings_discount.usecase.get;

import com.project.ccc_shop.common.Input;

public class GetSeasoningsDiscountInput implements Input {
    private int discountCode;

    public int getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(int discountCode) {
        this.discountCode = discountCode;
    }
}
