package com.project.ccc_shop.order.usecase.update;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;

import java.sql.Timestamp;
import java.util.Map;

public class UpdateOrderInput implements Input {
    private int orderId;
    private Status status;
    private Timestamp time;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
