package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.usecase.get.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetProductUseCaseTest {
    @Test
    public void get_product_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        GetProductUseCase getProductUseCase = new GetProductUseCase(mySQLDriver);
        GetProductInput input = new GetProductInput();
        GetProductOutput output = new GetProductOutput();

        input.setId(3);

        getProductUseCase.execute(input, output);

        assertNotNull(output.getName());
        assertEquals(1, output.getUserId());
        assertEquals("ipad", output.getName());
        assertEquals("TABLET", output.getCategory());
        assertEquals(20000, output.getPrice());
        assertEquals(3, output.getStock());
        assertEquals("address", output.getWarehouseAddress());
        assertEquals("great product in your life", output.getDescription());
        assertEquals("https://picsum.photos/200", output.getPictureURL());
    }

}
