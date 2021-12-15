package com.project.ccc_shop.order;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.order.entity.Payment;
import com.project.ccc_shop.order.entity.Status;
import com.project.ccc_shop.order.usecase.create.CreateOrderInput;
import com.project.ccc_shop.order.usecase.create.CreateOrderOutput;
import com.project.ccc_shop.order.usecase.create.CreateOrderUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        Timestamp orderTime = Timestamp.from(Instant.now());
        input.setOrderTime(orderTime);
        Map<Integer, Integer> orderItems = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            orderItems.put(i, 2);
        }
        input.setOrderItems(orderItems);

        createOrderUseCase.execute(input, output);

        assertNotNull(output.getId());
        assertEquals(orderTime, output.getOrderTime());
    }

    @Test
    public void create_order_with_seasoning_discount_code() {
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
        Timestamp orderTime = Timestamp.from(Instant.now());
        input.setOrderTime(orderTime);
        input.setSeasoningDiscountCode(3);
        Map<Integer, Integer> orderItems = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            orderItems.put(i, 8 - i);
        }
        input.setOrderItems(orderItems);

        createOrderUseCase.execute(input, output);

        assertNotNull(output.getId());
        assertEquals(orderTime, output.getOrderTime());
    }

    @Test
    public void create_order_with_shipping_discount_code() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(mySQLDriver);
        CreateOrderInput input = new CreateOrderInput();
        CreateOrderOutput output = new CreateOrderOutput();
        input.setCustomerId(3);
        input.setShippingFee(80);
        input.setRecipientName("Cindy");
        input.setShippingAddress("some where");
        input.setStatus(Status.ORDER);
        input.setPaymentMethod(Payment.CASH);
        Timestamp orderTime = Timestamp.from(Instant.now());
        input.setOrderTime(orderTime);
        input.setShippingDiscountCode(2);
        Map<Integer, Integer> orderItems = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            orderItems.put(4 - i, i);
        }
        input.setOrderItems(orderItems);

        createOrderUseCase.execute(input, output);

        assertNotNull(output.getId());
        assertEquals(orderTime, output.getOrderTime());
    }
}
