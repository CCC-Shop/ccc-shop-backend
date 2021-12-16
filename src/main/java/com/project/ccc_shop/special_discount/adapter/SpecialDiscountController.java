package com.project.ccc_shop.special_discount.adapter;

import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/special_discount")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SpecialDiscountController {

    CreateSpecialDiscountUseCase createSpecialDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateSpecialDiscountUseCase createSpecialDiscountUseCase) {
        this.createSpecialDiscountUseCase = createSpecialDiscountUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateSpecialDiscountOutput> createSpecialDiscount(@RequestBody CreateSpecialDiscountInput requestBody) {
        CreateSpecialDiscountOutput output = new CreateSpecialDiscountOutput();

        try {
            this.createSpecialDiscountUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
