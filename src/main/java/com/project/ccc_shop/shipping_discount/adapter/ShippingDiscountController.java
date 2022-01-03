package com.project.ccc_shop.shipping_discount.adapter;

import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountUseCase;
import com.project.ccc_shop.shipping_discount.usecase.get_current.GetCurrentShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.get_current.GetCurrentShippingDiscountUseCase;
import com.project.ccc_shop.shipping_discount.usecase.edit.EditShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.edit.EditShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.edit.EditShippingDiscountUseCase;
import com.project.ccc_shop.shipping_discount.usecase.get_vender.GetVenderShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.get_vender.GetVenderShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.get_vender.GetVenderShippingDiscountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping_discount")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ShippingDiscountController {

    CreateShippingDiscountUseCase createShippingDiscountUseCase;
    GetCurrentShippingDiscountUseCase getCurrentShippingDiscountUseCase;
    GetVenderShippingDiscountUseCase getVenderShippingDiscountUseCase;
    EditShippingDiscountUseCase editShippingDiscountUseCase;

    @Autowired
    public void setCreateDiscountUseCase(CreateShippingDiscountUseCase createShippingDiscountUseCase) {
        this.createShippingDiscountUseCase = createShippingDiscountUseCase;
    }

    @Autowired
    public void setGetCurrentShippingDiscountUseCase(GetCurrentShippingDiscountUseCase getCurrentShippingDiscountUseCase) {
        this.getCurrentShippingDiscountUseCase = getCurrentShippingDiscountUseCase;
    }

    @Autowired
    public void setGetVenderShippingDiscountUseCase(GetVenderShippingDiscountUseCase getVenderShippingDiscountUseCase) {
        this.getVenderShippingDiscountUseCase = getVenderShippingDiscountUseCase;
    }

    @Autowired
    public void setEditShippingDiscountUseCase(EditShippingDiscountUseCase editShippingDiscountUseCase) {
        this.editShippingDiscountUseCase = editShippingDiscountUseCase;
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

    @GetMapping(value = "/get-current")
    public ResponseEntity<GetCurrentShippingDiscountOutput> getCurrentShippingDiscount() {
        GetCurrentShippingDiscountOutput output = new GetCurrentShippingDiscountOutput();

        try {
            this.getCurrentShippingDiscountUseCase.execute(output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get-vender")
    public ResponseEntity<GetVenderShippingDiscountOutput> getVenderShippingDiscount(@RequestBody GetVenderShippingDiscountInput requestBody) {
        GetVenderShippingDiscountOutput output = new GetVenderShippingDiscountOutput();

        try {
            this.getVenderShippingDiscountUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<EditShippingDiscountOutput> editShippingDiscount(@RequestBody EditShippingDiscountInput requestBody) {

        EditShippingDiscountInput input = new EditShippingDiscountInput();
        EditShippingDiscountOutput output = new EditShippingDiscountOutput();
        try {
            input.setDiscountCode(requestBody.getDiscountCode());
            input.setVenderId(requestBody.getVenderId());
            input.setPolicyDescription(requestBody.getPolicyDescription());
            input.setStartTime(requestBody.getStartTime());
            input.setEndTime(requestBody.getEndTime());
            input.setTargetPrice(requestBody.getTargetPrice());

            this.editShippingDiscountUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
