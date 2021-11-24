package com.project.ccc_shop.product.adapter;

import com.project.ccc_shop.product.usecase.create.CreateProductInput;
import com.project.ccc_shop.product.usecase.create.CreateProductOutput;
import com.project.ccc_shop.product.usecase.create.CreateProductUseCase;
import com.project.ccc_shop.product.usecase.update.UpdateProductInput;
import com.project.ccc_shop.product.usecase.update.UpdateProductOutput;
import com.project.ccc_shop.product.usecase.update.UpdateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
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

    @PostMapping(value = "/add")
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
            return ResponseEntity.status(HttpStatus.OK).body(output);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/update")
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
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

}
