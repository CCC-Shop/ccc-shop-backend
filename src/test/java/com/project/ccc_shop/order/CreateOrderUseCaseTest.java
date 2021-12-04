package com.project.ccc_shop.order;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;
import com.project.ccc_shop.order.usecase.CreateOrderInput;
import com.project.ccc_shop.order.usecase.CreateOrderOutput;
import com.project.ccc_shop.order.usecase.CreateOrderUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateOrderUseCaseTest {
    @Test
    public void create_order_without_discount_code() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(mySQLDriver);
        CreateOrderInput input = new CreateOrderInput();
        CreateOrderOutput output = new CreateOrderOutput();

        input.setCustomerId(1);
        input.setShippingFee(60);
        input.setRecipientName("Jack");
        input.setShippingAddress("台北市大安區忠孝東路xxx號");
        input.setStatus(Status.ORDER);
        input.setPaymentMethod(Payment.CASH);
        input.setOrderTime(Timestamp.from(Instant.parse("2021-12-04T11:00:00Z")));
        Map<Integer, Integer> orderItems = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            orderItems.put(i, 2);
        }
        input.setOrderItems(orderItems);

        createOrderUseCase.execute(input, output);

        assertNotNull(output.getId());
    }

    @Test
    public void create_order_with_discount_code() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(mySQLDriver);
        CreateOrderInput input = new CreateOrderInput();
        CreateOrderOutput output = new CreateOrderOutput();

        input.setCustomerId(2);
        input.setShippingFee(100);
        input.setRecipientName("Bob");
        input.setShippingAddress("some where");
        input.setStatus(Status.ORDER);
        input.setPaymentMethod(Payment.CASH);
        input.setOrderTime(Timestamp.from(Instant.parse("2021-12-04T11:00:00Z")));
        input.setSeasoningDiscountCode(3);
        Map<Integer, Integer> orderItems = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            orderItems.put(i, 8-i);
        }
        input.setOrderItems(orderItems);

        createOrderUseCase.execute(input, output);

        assertNotNull(output.getId());
    }
}
