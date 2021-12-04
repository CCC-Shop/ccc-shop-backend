package com.project.ccc_shop.order.adapter;

import com.project.ccc_shop.order.usecase.create.CreateOrderInput;
import com.project.ccc_shop.order.usecase.create.CreateOrderOutput;
import com.project.ccc_shop.order.usecase.create.CreateOrderUseCase;
import com.project.ccc_shop.order.usecase.update.UpdateOrderInput;
import com.project.ccc_shop.order.usecase.update.UpdateOrderOutput;
import com.project.ccc_shop.order.usecase.update.UpdateOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class OrderController {

    CreateOrderUseCase createOrderUseCase;
    UpdateOrderUseCase updateOrderUseCase;

    @Autowired
    public void setCreateOrderUseCase(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Autowired
    public void setUpdateOrderUseCase(UpdateOrderUseCase updateOrderUseCase) {
        this.updateOrderUseCase = updateOrderUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateOrderOutput> createOrder(@RequestBody CreateOrderInput requestBody) {

        CreateOrderInput input = new CreateOrderInput();
        CreateOrderOutput output = new CreateOrderOutput();
        try {
            input.setCustomerId(requestBody.getCustomerId());
            input.setShippingFee(requestBody.getShippingFee());
            input.setRecipientName(requestBody.getRecipientName());
            input.setShippingAddress(requestBody.getShippingAddress());
            input.setStatus(requestBody.getStatus());
            input.setPaymentMethod(requestBody.getPaymentMethod());
            input.setOrderTime(requestBody.getOrderTime());
            input.setOrderItems(requestBody.getOrderItems());
            input.setSeasoningDiscountCode(requestBody.getSeasoningDiscountCode());
            input.setShippingDiscountCode(requestBody.getShippingDiscountCode());

            this.createOrderUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UpdateOrderOutput> updateOrder(@RequestBody UpdateOrderInput requestBody) {

        UpdateOrderInput input = new UpdateOrderInput();
        UpdateOrderOutput output = new UpdateOrderOutput();
        try {
            input.setOrderId(requestBody.getOrderId());
            input.setStatus(requestBody.getStatus());
            input.setTime(requestBody.getTime());

            this.updateOrderUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
