package com.project.ccc_shop.order.usecase.get;

import com.project.ccc_shop.common.Input;

public class GetVenderOrderInput implements Input {
    private int venderId;

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }
}
