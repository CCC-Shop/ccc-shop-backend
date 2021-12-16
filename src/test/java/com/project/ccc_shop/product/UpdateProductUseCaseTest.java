package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.usecase.update.UpdateProductInput;
import com.project.ccc_shop.product.usecase.update.UpdateProductOutput;
import com.project.ccc_shop.product.usecase.update.UpdateProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateProductUseCaseTest {
    @Test
    public void update_product_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(mySQLDriver);
        UpdateProductInput input = new UpdateProductInput();
        UpdateProductOutput output = new UpdateProductOutput();

        input.setId(1);
        input.setName("ipad2");
        input.setUserId(2);
        input.setCategory(Category.TABLET);
        input.setPrice(20000);
        input.setStock(3);
        input.setWarehouseAddress("台北市大安區忠孝東路xxx號");
        input.setDescription("great product in your life");
        input.setPictureURL("https://picsum.photos/200");

        updateProductUseCase.execute(input, output);

        assertNotNull(output.getName());
        assertEquals("ipad2", output.getName());
    }
}
