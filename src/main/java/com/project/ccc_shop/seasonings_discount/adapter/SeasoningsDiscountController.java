package com.project.ccc_shop.seasonings_discount.adapter;

import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountUseCase;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seasonings_discount")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SeasoningsDiscountController {

    CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase;
    GetCurrentSeasoningsDiscountUseCase getCurrentSeasoningsDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase) {
        this.createSeasoningsDiscountUseCase = createSeasoningsDiscountUseCase;
    }

    @Autowired
    public void setGetCurrentSeasoningsDiscountUseCase(GetCurrentSeasoningsDiscountUseCase getCurrentSeasoningsDiscountUseCase) {
        this.getCurrentSeasoningsDiscountUseCase = getCurrentSeasoningsDiscountUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateSeasoningsDiscountOutput> createSeasoningsDiscount(@RequestBody CreateSeasoningsDiscountInput requestBody) {
        CreateSeasoningsDiscountOutput output = new CreateSeasoningsDiscountOutput();

        try {
            this.createSeasoningsDiscountUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @GetMapping(value = "/get-current")
    public ResponseEntity<GetCurrentSeasoningsDiscountOutput> getCurrentSeasoningsDiscount() {
        GetCurrentSeasoningsDiscountOutput output = new GetCurrentSeasoningsDiscountOutput();

        try {
            this.getCurrentSeasoningsDiscountUseCase.execute(output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
