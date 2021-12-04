package com.project.ccc_shop.order.usecase.create;

import com.project.ccc_shop.common.Output;

public class CreateOrderOutput implements Output {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
