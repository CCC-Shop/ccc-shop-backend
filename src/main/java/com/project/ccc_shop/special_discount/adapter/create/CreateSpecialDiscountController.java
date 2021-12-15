package com.project.ccc_shop.special_discount.adapter.create;

import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/special_discount")
public class CreateSpecialDiscountController {

    CreateSpecialDiscountUseCase createSpecialDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateSpecialDiscountUseCase createSpecialDiscountUseCase) {
        this.createSpecialDiscountUseCase = createSpecialDiscountUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateSpecialDiscountOutput> createSpecialDiscount(@RequestBody CreateSpecialDiscountInput requestBody) {
        CreateSpecialDiscountInput input = new CreateSpecialDiscountInput();
        CreateSpecialDiscountOutput output = new CreateSpecialDiscountOutput();
        try {

//        input.setDiscountCode(requestBody.getDiscountCode());
            input.setProductId(requestBody.getProductId());
            input.setVenderId(requestBody.getVenderId());
            input.setPolicyDescription(requestBody.getPolicyDescription());
            input.setStartTime(requestBody.getStartTime());
            input.setEndTime(requestBody.getEndTime());
            input.setCategory(requestBody.getCategory());

            this.createSpecialDiscountUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
