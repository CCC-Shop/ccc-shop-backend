package com.project.ccc_shop.valuation.usecase.create;

import com.project.ccc_shop.common.Output;

public class CreateValuationOutput extends Output {
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
