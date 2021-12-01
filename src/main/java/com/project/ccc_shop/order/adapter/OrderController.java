package com.project.ccc_shop.order.adapter;

import com.project.ccc_shop.order.usecase.CreateOrderInput;
import com.project.ccc_shop.order.usecase.CreateOrderOutput;
import com.project.ccc_shop.order.usecase.CreateOrderUseCase;
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

            this.createOrderUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }

//        CreateOrderInput input = new CreateOrderInput();
//        CreateOrderOutput output = new CreateOrderOutput();
//        try {
//            JSONObject orderInfoJSON = new JSONObject(orderInfo);
//            input.setCustomerId(orderInfoJSON.getInt("customerId"));
//            input.setShippingFee(orderInfoJSON.getInt("shippingFee"));
//            input.setRecipientName(orderInfoJSON.getString("recipientName"));
//            input.setShippingAddress(orderInfoJSON.getString("shippingAddress"));
//            input.setStatus(Status.valueOf(orderInfoJSON.getString("status")));
//            input.setPaymentMethod(Payment.valueOf(orderInfoJSON.getString("paymentMethod")));
//            input.setOrderTime(Timestamp.valueOf(orderInfoJSON.getString("orderTime")));
//            JSONArray orderItems = orderInfoJSON.getJSONArray("orderItems");
//
//            input.setOrderItems();
//
//            this.createOrderUseCase.execute(input, output);
//
//            return ResponseEntity.status(HttpStatus.OK).body(output);
//        } catch (JSONException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
//        }
    }
}
