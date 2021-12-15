package com.project.ccc_shop.seasonings_discount.adapter;

import com.project.ccc_shop.seasonings_discount.usecase.get.GetSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.get.GetSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountUseCase;
import com.project.ccc_shop.seasonings_discount.usecase.get.GetSeasoningsDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seasonings_discount")
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class SeasoningsDiscountController {

    CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase;
    GetSeasoningsDiscountUseCase getSeasoningsDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase) {
        this.createSeasoningsDiscountUseCase = createSeasoningsDiscountUseCase;
    }

    @Autowired
    public void setGetSeasoningsDiscountUseCase(GetSeasoningsDiscountUseCase getSeasoningsDiscountUseCase) {
        this.getSeasoningsDiscountUseCase = getSeasoningsDiscountUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateSeasoningsDiscountOutput> createSeasoningsDiscount(@RequestBody CreateSeasoningsDiscountInput requestBody) {
        CreateSeasoningsDiscountInput input = new CreateSeasoningsDiscountInput();
        CreateSeasoningsDiscountOutput output = new CreateSeasoningsDiscountOutput();
        try {

//        input.setDiscountCode(requestBody.getDiscountCode());
            input.setVenderId(requestBody.getVenderId());
            input.setPolicyDescription(requestBody.getPolicyDescription());
            input.setStartTime(requestBody.getStartTime());
            input.setEndTime(requestBody.getEndTime());
            input.setDiscountRate(requestBody.getDiscountRate());

            this.createSeasoningsDiscountUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get")
    public ResponseEntity<GetSeasoningsDiscountOutput> getSeasoningsDiscount(@RequestBody GetSeasoningsDiscountInput requestBody) {
        GetSeasoningsDiscountInput input = new GetSeasoningsDiscountInput();
        GetSeasoningsDiscountOutput output = new GetSeasoningsDiscountOutput();

        input.setDiscountCode(requestBody.getDiscountCode());

        try {
            this.getSeasoningsDiscountUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
