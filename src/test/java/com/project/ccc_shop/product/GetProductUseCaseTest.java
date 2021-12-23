package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.usecase.get.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetProductUseCaseTest {
    final static String SUCCESS_MESSAGE = "Success!";

    @Test
    public void get_product_succeeded() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetProductUseCase getProductUseCase = new GetProductUseCase(mySQLDriver);
        GetProductInput input = new GetProductInput();
        GetProductOutput output = new GetProductOutput();
        input.setId(3);

        getProductUseCase.execute(input, output);

        assertEquals(SUCCESS_MESSAGE, output.getMessage());
        assertEquals(2, output.getVenderId());
        assertEquals("iPhone 13 Pro", output.getName());
        assertEquals(Category.PHONE, output.getCategory());
        assertEquals(32900, output.getPrice());
        assertEquals(200, output.getStock());
        assertEquals("XX市XX區XX路XX號XX樓", output.getWarehouseAddress());
        assertEquals("這是很貴的蘋果手機", output.getDescription());
        assertEquals("https://web-eshop.cdn.hinet.net/eShop%20Images/Product/phones/000100004254/154632-C50321583002.jpg", output.getPictureURL());
        assertTrue(output.getExistFlag());
    }

    @Test
    public void get_product_failed_caused_by_product_does_not_exist() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetProductUseCase getProductUseCase = new GetProductUseCase(mySQLDriver);
        GetProductInput input = new GetProductInput();
        GetProductOutput output = new GetProductOutput();
        input.setId(100);

        assertThrows(RuntimeException.class, () -> {
            getProductUseCase.execute(input, output);
        });
    }
}
