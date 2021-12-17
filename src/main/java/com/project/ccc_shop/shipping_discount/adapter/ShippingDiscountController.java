package com.project.ccc_shop.shipping_discount.adapter;

import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping_discount")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ShippingDiscountController {

    CreateShippingDiscountUseCase createShippingDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateShippingDiscountUseCase createShippingDiscountUseCase) {
        this.createShippingDiscountUseCase = createShippingDiscountUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateShippingDiscountOutput> createShippingDiscount(@RequestBody CreateShippingDiscountInput requestBody) {
        CreateShippingDiscountOutput output = new CreateShippingDiscountOutput();

        try {
            this.createShippingDiscountUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
