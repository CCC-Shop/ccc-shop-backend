package com.project.ccc_shop.order.usecase.get;

import com.project.ccc_shop.common.Input;

public class GetCustomerOrderInput implements Input {
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
