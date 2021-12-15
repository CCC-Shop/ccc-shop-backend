package com.project.ccc_shop.shopping_cart.adapter;

import com.project.ccc_shop.shopping_cart.usecase.create.CreateShoppingCartInput;
import com.project.ccc_shop.shopping_cart.usecase.create.CreateShoppingCartOutput;
import com.project.ccc_shop.shopping_cart.usecase.create.CreateShoppingCartUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-cart")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ShoppingCartController {

    CreateShoppingCartUseCase createShoppingCartUseCase;

    @Autowired
    public void setCreateShoppingCartUseCase(CreateShoppingCartUseCase createShoppingCartUseCase) {
        this.createShoppingCartUseCase = createShoppingCartUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateShoppingCartOutput> createOrder(@RequestBody CreateShoppingCartInput requestBody) {

        CreateShoppingCartInput input = new CreateShoppingCartInput();
        CreateShoppingCartOutput output = new CreateShoppingCartOutput();
        try {
            input.setProductId(requestBody.getProductId());
            input.setCustomerId(requestBody.getCustomerId());
            input.setQuantity(requestBody.getQuantity());

            this.createShoppingCartUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
