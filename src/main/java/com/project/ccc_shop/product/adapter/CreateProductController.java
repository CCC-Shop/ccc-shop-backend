package com.project.ccc_shop.product.adapter;


import com.project.ccc_shop.product.usecase.CreateProductInput;
import com.project.ccc_shop.product.usecase.CreateProductOutput;
import com.project.ccc_shop.product.usecase.CreateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
public class CreateProductController {

    CreateProductUseCase createProductUseCase;

    @Autowired
    public void setCreateProductUseCase(CreateProductUseCase createProductUseCase){
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping(value = "/add/product")
    public ResponseEntity<CreateProductOutput> addProduct(@RequestBody CreateProductInput requestBody) {
        CreateProductInput input = new CreateProductInput();
        CreateProductOutput output = new CreateProductOutput();

        input.setName(requestBody.getName());
        input.setUserId(requestBody.getUserId());
        input.setCategory(requestBody.getCategory());
        input.setStock(requestBody.getStock());
        input.setPrice(requestBody.getPrice());
        input.setDescription(requestBody.getDescription());
        input.setPictureURL(requestBody.getPictureURL());

        try {
            this.createProductUseCase.execute(input, output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }
}
