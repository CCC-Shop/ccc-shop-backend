package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.usecase.delete.DeleteProductInput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductOutput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteProductUseCaseTest {
    final static String SUCCESS_MESSAGE = "Success!";

    @Test
    public void delete_product_succeeded() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(mySQLDriver);
        DeleteProductInput input = new DeleteProductInput();
        DeleteProductOutput output = new DeleteProductOutput();
        input.setId(4);

        deleteProductUseCase.execute(input, output);

        assertEquals(SUCCESS_MESSAGE, output.getMessage());
    }

    @Test
    public void delete_product_failed_caused_by_product_does_not_exist() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(mySQLDriver);
        DeleteProductInput input = new DeleteProductInput();
        DeleteProductOutput output = new DeleteProductOutput();
        input.setId(100);

        assertThrows(RuntimeException.class, () -> {
            deleteProductUseCase.execute(input, output);
        });
    }
}