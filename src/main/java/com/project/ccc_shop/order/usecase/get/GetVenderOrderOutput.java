package com.project.ccc_shop.order.usecase.get;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.order.entity.Order;

import java.util.List;

public class GetVenderOrderOutput extends Output {
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
