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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateOrderUseCaseTest {
    @Test
    public void create_order_use_case() {
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
        input.setOrderTime(Timestamp.from(Instant.parse("2021-11-23T10:00:00Z")));
//        input.setOrderTime(Timestamp.from(Instant.now()));
        List<Integer> orderItemIds = new ArrayList<>();
        orderItemIds.add(1);
        orderItemIds.add(2);
        orderItemIds.add(3);
        input.setOrderItemIds(orderItemIds);
        // <productId, quantity>
        Map<Integer, Integer> orderItems = new HashMap<>();
        orderItemIds.forEach(productId -> orderItems.put(productId, 2));
        input.setOrderItems(orderItems);

        createOrderUseCase.execute(input, output);

        assertNotNull(output.getId());
    }
}
