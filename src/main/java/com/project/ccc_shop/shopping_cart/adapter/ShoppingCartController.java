package com.project.ccc_shop.shopping_cart.adapter;

import com.project.ccc_shop.shopping_cart.usecase.create.CreateShoppingCartInput;
import com.project.ccc_shop.shopping_cart.usecase.create.CreateShoppingCartOutput;
import com.project.ccc_shop.shopping_cart.usecase.create.CreateShoppingCartUseCase;
import com.project.ccc_shop.shopping_cart.usecase.get.GetShoppingCartItemsInput;
import com.project.ccc_shop.shopping_cart.usecase.get.GetShoppingCartItemsOutput;
import com.project.ccc_shop.shopping_cart.usecase.get.GetShoppingCartItemsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-cart")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ShoppingCartController {

    CreateShoppingCartUseCase createShoppingCartUseCase;
    GetShoppingCartItemsUseCase getShoppingCartItemsUseCase;

    @Autowired
    public void setCreateShoppingCartUseCase(CreateShoppingCartUseCase createShoppingCartUseCase) {
        this.createShoppingCartUseCase = createShoppingCartUseCase;
    }

    @Autowired
    public void setGetShoppingCartItemsUseCase(GetShoppingCartItemsUseCase getShoppingCartItemsUseCase) {
        this.getShoppingCartItemsUseCase = getShoppingCartItemsUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateShoppingCartOutput> createShoppingCart(@RequestBody CreateShoppingCartInput requestBody) {

        CreateShoppingCartInput input = new CreateShoppingCartInput();
        CreateShoppingCartOutput output = new CreateShoppingCartOutput();
        try {
            input.setProductId(requestBody.getProductId());
            input.setCustomerId(requestBody.getCustomerId());
            input.setQuantity(requestBody.getQuantity());

            this.createShoppingCartUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get")
    public ResponseEntity<GetShoppingCartItemsOutput> getShoppingCartItemsUseCase(@RequestBody GetShoppingCartItemsInput requestBody) {

        GetShoppingCartItemsInput input = new GetShoppingCartItemsInput();
        GetShoppingCartItemsOutput output = new GetShoppingCartItemsOutput();
        try {
            input.setId(requestBody.getId());

            this.getShoppingCartItemsUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
