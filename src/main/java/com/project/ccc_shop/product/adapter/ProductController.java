package com.project.ccc_shop.product.adapter;

import com.project.ccc_shop.product.usecase.CreateProductInput;
import com.project.ccc_shop.product.usecase.CreateProductOutput;
import com.project.ccc_shop.product.usecase.CreateProductUseCase;
import com.project.ccc_shop.product.usecase.UpdateProductInput;
import com.project.ccc_shop.product.usecase.UpdateProductOutput;
import com.project.ccc_shop.product.usecase.UpdateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
public class ProductController {

    CreateProductUseCase createProductUseCase;
    UpdateProductUseCase updateProductUseCase;

    @Autowired
    public void setCreateProductUseCase(CreateProductUseCase createProductUseCase){
        this.createProductUseCase = createProductUseCase;
    }

    @Autowired
    public void setUpdateProductUseCase(UpdateProductUseCase updateProductUseCase){
        this.updateProductUseCase = updateProductUseCase;
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
        input.setWarehouseAddress(requestBody.getWarehouseAddress());

        try {
            this.createProductUseCase.execute(input, output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @PostMapping(value = "/update/product")
    public ResponseEntity<UpdateProductOutput> updateProduct(@RequestBody UpdateProductInput requestBody) {
        UpdateProductInput input = new UpdateProductInput();
        UpdateProductOutput output = new UpdateProductOutput();

        input.setId(requestBody.getId());
        input.setName(requestBody.getName());
        input.setUserId(requestBody.getUserId());
        input.setCategory(requestBody.getCategory());
        input.setStock(requestBody.getStock());
        input.setPrice(requestBody.getPrice());
        input.setDescription(requestBody.getDescription());
        input.setPictureURL(requestBody.getPictureURL());
        input.setWarehouseAddress(requestBody.getWarehouseAddress());

        try {
            this.updateProductUseCase.execute(input, output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

}