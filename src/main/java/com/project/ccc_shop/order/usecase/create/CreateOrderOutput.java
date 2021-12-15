package com.project.ccc_shop.order.usecase.create;

import com.project.ccc_shop.common.Output;

import java.sql.Timestamp;

public class CreateOrderOutput extends Output {
    private int id;
    private Timestamp orderTime;

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
