package com.project.ccc_shop.order;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.order.entity.Status;
import com.project.ccc_shop.order.usecase.update.UpdateOrderInput;
import com.project.ccc_shop.order.usecase.update.UpdateOrderOutput;
import com.project.ccc_shop.order.usecase.update.UpdateOrderUseCase;
import jdk.jshell.Snippet;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateOrderUseCaseTest {
    @Test
    public void update_order_to_shipping_state() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        UpdateOrderUseCase updateOrderUseCase = new UpdateOrderUseCase(mySQLDriver);
        UpdateOrderInput input = new UpdateOrderInput();
        UpdateOrderOutput output = new UpdateOrderOutput();

        input.setOrderId(6);
        input.setStatus(Status.valueOf("SHIPPING"));
        Timestamp time = Timestamp.from(Instant.now());
        input.setTime(time);

        updateOrderUseCase.execute(input, output);

        assertEquals(time, output.getTime());
    }

    @Test
    public void update_order_to_delivered_state() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        UpdateOrderUseCase updateOrderUseCase = new UpdateOrderUseCase(mySQLDriver);
        UpdateOrderInput input = new UpdateOrderInput();
        UpdateOrderOutput output = new UpdateOrderOutput();

        input.setOrderId(7);
        input.setStatus(Status.valueOf("DELIVERED"));
        Timestamp time = Timestamp.from(Instant.now());
        input.setTime(time);

        updateOrderUseCase.execute(input, output);

        assertEquals(time, output.getTime());
    }

    @Test
    public void update_order_to_received_state() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        UpdateOrderUseCase updateOrderUseCase = new UpdateOrderUseCase(mySQLDriver);
        UpdateOrderInput input = new UpdateOrderInput();
        UpdateOrderOutput output = new UpdateOrderOutput();

        input.setOrderId(8);
        input.setStatus(Status.valueOf("RECEIVED"));
        Timestamp time = Timestamp.from(Instant.now());
        input.setTime(time);

        updateOrderUseCase.execute(input, output);

        assertEquals(time, output.getTime());
    }
}
