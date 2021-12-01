package com.project.ccc_shop.seasonings_discount.adapter;

import com.project.ccc_shop.seasonings_discount.usecase.CreateSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.CreateSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.CreateSeasoningsDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seasonings_discount")
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class CreateSeasoningsDiscountController {

    CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase){
        this.createSeasoningsDiscountUseCase = createSeasoningsDiscountUseCase;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<CreateSeasoningsDiscountOutput> addDiscount(@RequestBody CreateSeasoningsDiscountInput requestBody) {
        CreateSeasoningsDiscountInput input = new CreateSeasoningsDiscountInput();
        CreateSeasoningsDiscountOutput output = new CreateSeasoningsDiscountOutput();

//        input.setDiscountCode(requestBody.getDiscountCode());
        input.setOrderId(requestBody.getOrderId());
        input.setVenderId(requestBody.getVenderId());
        input.setPolicyDescription(requestBody.getPolicyDescription());
        input.setStartTime(requestBody.getStartTime());
        input.setEndTime(requestBody.getEndTime());
        input.setDiscountRate(requestBody.getDiscountRate());
//        input.setTargetPrice(requestBody.getTargetPrice());
//        input.setCategory(requestBody.getCategory());

        try {
            this.createSeasoningsDiscountUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
