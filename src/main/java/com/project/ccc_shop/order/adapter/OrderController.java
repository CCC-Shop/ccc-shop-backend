package com.project.ccc_shop.order.adapter;

import com.project.ccc_shop.order.usecase.create.CreateOrderInput;
import com.project.ccc_shop.order.usecase.create.CreateOrderOutput;
import com.project.ccc_shop.order.usecase.create.CreateOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class OrderController {

    CreateOrderUseCase createOrderUseCase;

    @Autowired
    public void setCreateOrderUseCase(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateOrderOutput> createOrder(@RequestBody CreateOrderInput orderInfo) {

        CreateOrderInput input = new CreateOrderInput();
        CreateOrderOutput output = new CreateOrderOutput();
        try {
            input.setCustomerId(orderInfo.getCustomerId());
            input.setShippingFee(orderInfo.getShippingFee());
            input.setRecipientName(orderInfo.getRecipientName());
            input.setShippingAddress(orderInfo.getShippingAddress());
            input.setStatus(orderInfo.getStatus());
            input.setPaymentMethod(orderInfo.getPaymentMethod());
            input.setOrderTime(orderInfo.getOrderTime());
            input.setOrderItems(orderInfo.getOrderItems());
            input.setSeasoningDiscountCode(orderInfo.getSeasoningDiscountCode());
            input.setShippingDiscountCode(orderInfo.getShippingDiscountCode());

            this.createOrderUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
