package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.usecase.update.UpdateProductInput;
import com.project.ccc_shop.product.usecase.update.UpdateProductOutput;
import com.project.ccc_shop.product.usecase.update.UpdateProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateProductUseCaseTest {
    final static String SUCCESS_MESSAGE = "Success!";

    @Test
    public void update_product_succeseed() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(mySQLDriver);
        UpdateProductInput input = new UpdateProductInput();
        UpdateProductOutput output = new UpdateProductOutput();
        input.setId(1);
        input.setName("ipad2");
        input.setVenderId(2);
        input.setCategory(Category.TABLET);
        input.setPrice(20000);
        input.setStock(0);
        input.setWarehouseAddress("台北市大安區忠孝東路xxx號");
        input.setDescription("great product in your life");
        input.setPictureURL("https://picsum.photos/200");
        input.setExistFlag(false);

        updateProductUseCase.execute(input, output);

        assertEquals(SUCCESS_MESSAGE, output.getMessage());
    }

    @Test
    public void update_product_failed_caused_by_product_does_not_exist() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(mySQLDriver);
        UpdateProductInput input = new UpdateProductInput();
        UpdateProductOutput output = new UpdateProductOutput();
        input.setId(100);
        input.setName("ipad2");
        input.setVenderId(2);
        input.setCategory(Category.TABLET);
        input.setPrice(20000);
        input.setStock(50);
        input.setWarehouseAddress("台北市大安區忠孝東路xxx號");
        input.setDescription("great product in your life");
        input.setPictureURL("https://picsum.photos/200");
        input.setExistFlag(false);

        assertThrows(RuntimeException.class, () -> {
            updateProductUseCase.execute(input, output);
        });
    }
}
