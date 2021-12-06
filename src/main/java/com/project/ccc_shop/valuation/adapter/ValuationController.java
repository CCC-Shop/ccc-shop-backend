package com.project.ccc_shop.valuation.adapter;

import com.project.ccc_shop.valuation.usecase.create.CreateValuationInput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationOutput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/valuation")
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class ValuationController {

    CreateValuationUseCase createValuationUseCase;

    @Autowired
    public void setCreateValuationUseCase(CreateValuationUseCase createValuationUseCase) {
        this.createValuationUseCase = createValuationUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateValuationOutput> createValuation(@RequestBody CreateValuationInput requestBody) {
        CreateValuationInput input = new CreateValuationInput();
        CreateValuationOutput output = new CreateValuationOutput();

        input.setCustomerId(requestBody.getCustomerId());
        input.setProductId(requestBody.getProductId());
        input.setComment(requestBody.getComment());
        input.setRating(requestBody.getRating());

        try {
            this.createValuationUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
