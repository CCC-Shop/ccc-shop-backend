package com.project.ccc_shop.order;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.order.entity.Order;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;
import com.project.ccc_shop.order.usecase.get.GetVenderOrderInput;
import com.project.ccc_shop.order.usecase.get.GetVenderOrderOutput;
import com.project.ccc_shop.order.usecase.get.GetVenderOrderUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVenderOrderUseCaseTest {
    @Test
    public void get_vender_orders() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetVenderOrderInput input = new GetVenderOrderInput();
        GetVenderOrderOutput output = new GetVenderOrderOutput();
        GetVenderOrderUseCase getVenderOrderUseCase = new GetVenderOrderUseCase(mySQLDriver);
        input.setVenderId(3);

        getVenderOrderUseCase.execute(input, output);
        List<Order> orderList = output.getOrderList();

        assertEquals(2, orderList.size());
        Order order = orderList.get(0);
        assertEquals(1, order.getId());
        assertEquals(6, order.getCustomerId());
        assertEquals(100, order.getShippingFee());
        assertEquals("Mandy", order.getRecipientName());
        assertEquals("台北市大安區忠孝東路xxx號5F", order.getShippingAddress());
        assertEquals(Status.RECEIVED, order.getStatus());
        assertEquals(Payment.MOBILE, order.getPaymentMethod());
        assertEquals(null, order.getCreditCardId());
        assertEquals(Timestamp.from(Instant.parse("2020-09-21T12:34:56Z")), order.getOrderTime());
        assertEquals(Timestamp.from(Instant.parse("2020-09-23T12:34:56Z")), order.getShippingTime());
        assertEquals(Timestamp.from(Instant.parse("2020-09-25T12:34:56Z")), order.getDeliveryTime());
        assertEquals(1, order.getSeasoningDiscountCode());
        assertEquals(0, order.getShippingDiscountCode());
        assertEquals(19810, order.getTotalPrice());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(1, order.getOrderItems().get(7));

        order = orderList.get(1);
        assertEquals(3, order.getId());
        assertEquals(120, order.getShippingFee());
        assertEquals(6, order.getCustomerId());
        assertEquals("Sandy", order.getRecipientName());
        assertEquals("台北市大安區忠孝東路xxx號5F", order.getShippingAddress());
        assertEquals(Status.DELIVERED, order.getStatus());
        assertEquals(Payment.CREDIT_CARD, order.getPaymentMethod());
        assertEquals("9999888-7777666", order.getCreditCardId());
        assertEquals(Timestamp.from(Instant.parse("2021-11-25T12:34:56Z")), order.getOrderTime());
        assertEquals(Timestamp.from(Instant.parse("2021-11-27T12:34:56Z")), order.getShippingTime());
        assertEquals(Timestamp.from(Instant.parse("2021-11-30T12:34:56Z")), order.getDeliveryTime());
        assertEquals(0, order.getSeasoningDiscountCode());
        assertEquals(0, order.getShippingDiscountCode());
        assertEquals(45120, order.getTotalPrice());
        assertEquals(1, order.getOrderItems().get(9));
    }
}
