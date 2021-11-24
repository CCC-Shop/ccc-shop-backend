package com.project.ccc_shop.discount.adapter;

import com.project.ccc_shop.discount.usecase.CreateDiscountInput;
import com.project.ccc_shop.discount.usecase.CreateDiscountOutput;
import com.project.ccc_shop.discount.usecase.CreateDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
public class CreateDiscountController {

    CreateDiscountUseCase createDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateDiscountUseCase createDiscountUseCase){
        this.createDiscountUseCase = createDiscountUseCase;
    }

    @PostMapping(value = "/add/product")
    public ResponseEntity<CreateDiscountOutput> addDiscount(@RequestBody CreateDiscountInput requestBody) {
        CreateDiscountInput input = new CreateDiscountInput();
        CreateDiscountOutput output = new CreateDiscountOutput();

        input.setDiscountCode(requestBody.getDiscountCode());
        input.setPolicyDescription(requestBody.getPolicyDescription());
        input.setStartTime(requestBody.getStartTime());
        input.setEndTime(requestBody.getEndTime());
        input.setDiscountRate(requestBody.getDiscountRate());
        input.setTargetPrice(requestBody.getTargetPrice());
        input.setCategory(requestBody.getCategory());

        try {
            this.createDiscountUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
