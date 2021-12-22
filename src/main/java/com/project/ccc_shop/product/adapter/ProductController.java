package com.project.ccc_shop.product.adapter;

import com.project.ccc_shop.product.usecase.create.CreateProductInput;
import com.project.ccc_shop.product.usecase.create.CreateProductOutput;
import com.project.ccc_shop.product.usecase.create.CreateProductUseCase;
import com.project.ccc_shop.product.usecase.delete.DeleteProductInput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductOutput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductUseCase;
import com.project.ccc_shop.product.usecase.get.GetProductInput;
import com.project.ccc_shop.product.usecase.get.GetProductOutput;
import com.project.ccc_shop.product.usecase.get.GetProductUseCase;
import com.project.ccc_shop.product.usecase.get_all.GetAllProductOutput;
import com.project.ccc_shop.product.usecase.get_all.GetAllProductUseCase;
import com.project.ccc_shop.product.usecase.update.UpdateProductInput;
import com.project.ccc_shop.product.usecase.update.UpdateProductOutput;
import com.project.ccc_shop.product.usecase.update.UpdateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class ProductController {

    CreateProductUseCase createProductUseCase;
    UpdateProductUseCase updateProductUseCase;
    DeleteProductUseCase deleteProductUseCase;
    GetProductUseCase getProductUseCase;
    GetAllProductUseCase getAllProductUseCase;

    @Autowired
    public void setCreateProductUseCase(CreateProductUseCase createProductUseCase){
        this.createProductUseCase = createProductUseCase;
    }

    @Autowired
    public void setUpdateProductUseCase(UpdateProductUseCase updateProductUseCase){
        this.updateProductUseCase = updateProductUseCase;
    }

    @Autowired
    public void setDeleteProductUseCase(DeleteProductUseCase deleteProductUseCase){
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @Autowired
    public void setGetProductUseCase(GetProductUseCase getProductUseCase){
        this.getProductUseCase = getProductUseCase;
    }

    @Autowired
    public void setGetAllProductUseCase(GetAllProductUseCase getAllProductUseCase){
        this.getAllProductUseCase = getAllProductUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateProductOutput> createProduct(@RequestBody CreateProductInput requestBody) {
        CreateProductInput input = new CreateProductInput();
        CreateProductOutput output = new CreateProductOutput();

        input.setName(requestBody.getName());
        input.setVenderId(requestBody.getVenderId());
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
            output.setMessage(e.getMessage());
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
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<DeleteProductOutput> deleteProduct(@RequestBody DeleteProductInput requestBody) {
        DeleteProductInput input = new DeleteProductInput();
        DeleteProductOutput output = new DeleteProductOutput();

        input.setId(requestBody.getId());

        try {
            this.deleteProductUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get")
    public ResponseEntity<GetProductOutput> getProduct(@RequestBody GetProductInput requestBody) {
        GetProductInput input = new GetProductInput();
        GetProductOutput output = new GetProductOutput();

        input.setId(requestBody.getId());

        try {
            this.getProductUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<GetAllProductOutput> getAllProduct() {
        GetAllProductOutput output = new GetAllProductOutput();

        try {
            this.getAllProductUseCase.execute(output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

}
