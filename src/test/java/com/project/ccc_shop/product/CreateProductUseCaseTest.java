package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.usecase.CreateProductInput;
import com.project.ccc_shop.product.usecase.CreateProductOutput;
import com.project.ccc_shop.product.usecase.CreateProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateProductUseCaseTest {
    @Test
    public void create_product_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateProductUseCase createProductBacklogUseCase = new CreateProductUseCase(mySQLDriver);
        CreateProductInput input = new CreateProductInput();
        CreateProductOutput output = new CreateProductOutput();

        input.setName("ipad");
        input.setCategory(Category.TABLET);
        input.setPrice(20000);
        input.setStock(3);
        input.setWarehouseAddress("台北市大安區忠孝東路xxx號");
        input.setDescription("great product in your life");
        input.setPictureURL("https://picsum.photos/200");

        createProductBacklogUseCase.execute(input, output);

        assertNotNull(output.getName());
        assertEquals("ipad", output.getName());
    }
}
