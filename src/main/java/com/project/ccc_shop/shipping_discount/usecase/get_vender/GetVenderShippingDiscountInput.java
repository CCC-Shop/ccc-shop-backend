package com.project.ccc_shop.shipping_discount.usecase.get_vender;

import com.project.ccc_shop.common.Input;

public class GetVenderShippingDiscountInput implements Input {
    private int venderId;

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }
}
