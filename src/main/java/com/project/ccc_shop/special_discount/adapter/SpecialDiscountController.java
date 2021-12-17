package com.project.ccc_shop.special_discount.adapter;

import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountUseCase;
import com.project.ccc_shop.special_discount.usecase.get_current.GetCurrentSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.get_current.GetCurrentSpecialDiscountUseCase;
import com.project.ccc_shop.user.usecase.get_all.GetAllUserOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/special_discount")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SpecialDiscountController {

    CreateSpecialDiscountUseCase createSpecialDiscountUseCase;
    GetCurrentSpecialDiscountUseCase getCurrentSpecialDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateSpecialDiscountUseCase createSpecialDiscountUseCase) {
        this.createSpecialDiscountUseCase = createSpecialDiscountUseCase;
    }

    @Autowired
    public void setGetCurrentSpecialDiscountUseCase(GetCurrentSpecialDiscountUseCase getCurrentSpecialDiscountUseCase) {
        this.getCurrentSpecialDiscountUseCase = getCurrentSpecialDiscountUseCase;
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

    @GetMapping(value = "/get-current")
    public ResponseEntity<GetCurrentSpecialDiscountOutput> getCurrentSpecialDiscount() {
        GetCurrentSpecialDiscountOutput output = new GetCurrentSpecialDiscountOutput();

        try {
            this.getCurrentSpecialDiscountUseCase.execute(output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}