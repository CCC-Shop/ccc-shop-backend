package com.project.ccc_shop.order;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.order.entity.Order;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;
import com.project.ccc_shop.order.usecase.get.GetCustomerOrderInput;
import com.project.ccc_shop.order.usecase.get.GetCustomerOrderOutput;
import com.project.ccc_shop.order.usecase.get.GetCustomerOrderUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetCustomerOrderUseCaseTest {
    @Test
    public void get_customer_order_history() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetCustomerOrderInput input = new GetCustomerOrderInput();
        GetCustomerOrderOutput output = new GetCustomerOrderOutput();
        GetCustomerOrderUseCase getCustomerOrderUseCase = new GetCustomerOrderUseCase(mySQLDriver);
        input.setCustomerId(5);

        getCustomerOrderUseCase.execute(input, output);
        List<Order> orderList = output.getOrderList();

        assertEquals(3, orderList.size());
        Order order = orderList.get(0);
        assertEquals(2, order.getId());
        assertEquals(0, order.getShippingFee());
        assertEquals("Zachary", order.getRecipientName());
        assertEquals("台北市大安區忠孝東路xxx號5F", order.getShippingAddress());
        assertEquals(Status.RECEIVED, order.getStatus());
        assertEquals(Payment.CASH, order.getPaymentMethod());
        assertEquals(null, order.getCreditCardId());
        assertEquals(Timestamp.from(Instant.parse("2021-11-11T12:34:56Z")), order.getOrderTime());
        assertEquals(Timestamp.from(Instant.parse("2021-11-12T12:34:56Z")), order.getShippingTime());
        assertEquals(Timestamp.from(Instant.parse("2021-11-15T12:34:56Z")), order.getDeliveryTime());
        assertEquals(0, order.getSeasoningDiscountCode());
        assertEquals(2, order.getShippingDiscountCode());
        assertEquals(16980, order.getTotalPrice());

        order = orderList.get(1);
        assertEquals(4, order.getId());
        assertEquals(10, order.getShippingFee());
        assertEquals("Zack", order.getRecipientName());
        assertEquals("台北市大安區忠孝東路xxx號5F", order.getShippingAddress());
        assertEquals(Status.SHIPPING, order.getStatus());
        assertEquals(Payment.CREDIT_CARD, order.getPaymentMethod());
        assertEquals("2222333-4444555", order.getCreditCardId());
        assertEquals(Timestamp.from(Instant.parse("2021-12-18T12:34:56Z")), order.getOrderTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-20T12:34:56Z")), order.getShippingTime());
        assertEquals(null, order.getDeliveryTime());
        assertEquals(0, order.getSeasoningDiscountCode());
        assertEquals(1, order.getShippingDiscountCode());
        assertEquals(14900, order.getTotalPrice());

        order = orderList.get(2);
        assertEquals(5, order.getId());
        assertEquals(0, order.getShippingFee());
        assertEquals("Zachary", order.getRecipientName());
        assertEquals("台北市大安區忠孝東路xxx號5F", order.getShippingAddress());
        assertEquals(Status.ORDER, order.getStatus());
        assertEquals(Payment.CASH, order.getPaymentMethod());
        assertEquals(null, order.getCreditCardId());
        assertEquals(Timestamp.from(Instant.parse("2021-12-25T12:34:56Z")), order.getOrderTime());
        assertEquals(null, order.getShippingTime());
        assertEquals(null, order.getDeliveryTime());
        assertEquals(0, order.getSeasoningDiscountCode());
        assertEquals(3, order.getShippingDiscountCode());
        assertEquals(36000, order.getTotalPrice());
    }
}
