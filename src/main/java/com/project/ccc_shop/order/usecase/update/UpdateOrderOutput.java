package com.project.ccc_shop.order.usecase.update;

import com.project.ccc_shop.common.Output;

import java.sql.Timestamp;

public class UpdateOrderOutput extends Output {
    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
