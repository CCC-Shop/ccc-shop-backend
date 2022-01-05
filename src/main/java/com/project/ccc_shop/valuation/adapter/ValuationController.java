package com.project.ccc_shop.valuation.adapter;

import com.project.ccc_shop.product.usecase.get.GetProductInput;
import com.project.ccc_shop.product.usecase.get.GetProductOutput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationInput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationOutput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationUseCase;
import com.project.ccc_shop.valuation.usecase.get.GetValuationInput;
import com.project.ccc_shop.valuation.usecase.get.GetValuationOutput;
import com.project.ccc_shop.valuation.usecase.get.GetValuationUseCase;
import com.project.ccc_shop.valuation.usecase.get_customer.GetCustomerValuationInput;
import com.project.ccc_shop.valuation.usecase.get_customer.GetCustomerValuationOutput;
import com.project.ccc_shop.valuation.usecase.get_customer.GetCustomerValuationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/valuation")
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class ValuationController {

    CreateValuationUseCase createValuationUseCase;
    GetValuationUseCase getValuationUseCase;
    GetCustomerValuationUseCase getCustomerValuationUseCase;

    @Autowired
    public void setCreateValuationUseCase(CreateValuationUseCase createValuationUseCase) {
        this.createValuationUseCase = createValuationUseCase;
    }

    @Autowired
    public void setGetValuationUseCase(GetValuationUseCase getValuationUseCase) {
        this.getValuationUseCase = getValuationUseCase;
    }

    @Autowired
    public void setGetCustomerValuationUseCase(GetCustomerValuationUseCase getCustomerValuationUseCase) {
        this.getCustomerValuationUseCase = getCustomerValuationUseCase;
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
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get")
    public ResponseEntity<GetValuationOutput> getValuation(@RequestBody GetValuationInput requestBody) {
        GetValuationInput input = new GetValuationInput();
        GetValuationOutput output = new GetValuationOutput();

        input.setProductId(requestBody.getProductId());

        try {
            this.getValuationUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get-customer")
    public ResponseEntity<GetCustomerValuationOutput> getCustomerValuation(@RequestBody GetCustomerValuationInput requestBody) {
        GetCustomerValuationOutput output = new GetCustomerValuationOutput();

        try {
            this.getCustomerValuationUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
