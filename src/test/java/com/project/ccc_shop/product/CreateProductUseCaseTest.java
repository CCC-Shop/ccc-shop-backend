package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.usecase.create.CreateProductInput;
import com.project.ccc_shop.product.usecase.create.CreateProductOutput;
import com.project.ccc_shop.product.usecase.create.CreateProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateProductUseCaseTest {
    final static String SUCCESS_MESSAGE = "Success!";

    @Test
    public void create_product_succeeded() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        CreateProductUseCase createProductUseCase = new CreateProductUseCase(mySQLDriver);
        CreateProductInput input = new CreateProductInput();
        CreateProductOutput output = new CreateProductOutput();
        input.setName("ipad");
        input.setVenderId(6);
        input.setCategory(Category.TABLET);
        input.setPrice(20000);
        input.setStock(3);
        input.setWarehouseAddress("address");
        input.setDescription("great product in your life");
        input.setPictureURL("https://picsum.photos/200");

        createProductUseCase.execute(input, output);

        assertEquals(SUCCESS_MESSAGE, output.getMessage());
    }

    @Test
    public void create_product_failed_caused_by_no_enough_product_info() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        CreateProductUseCase createProductUseCase = new CreateProductUseCase(mySQLDriver);
        CreateProductInput input = new CreateProductInput();
        CreateProductOutput output = new CreateProductOutput();
        input.setCategory(Category.TABLET);
        input.setPrice(20000);
        input.setStock(3);
        input.setWarehouseAddress("address");
        input.setDescription("great product in your life");

        assertThrows(RuntimeException.class, () -> {
            createProductUseCase.execute(input, output);
        });
    }
}
