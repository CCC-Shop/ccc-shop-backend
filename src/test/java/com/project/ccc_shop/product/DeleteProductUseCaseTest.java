package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.usecase.delete.DeleteProductInput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductOutput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteProductUseCaseTest {
    @Test
    public void delete_product_Succeed() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(mySQLDriver);
        DeleteProductInput input = new DeleteProductInput();
        DeleteProductOutput output = new DeleteProductOutput();

        input.setId(4);

        deleteProductUseCase.execute(input, output);

        assertTrue(output.getWorkCheck());
    }

    @Test
    public void delete_product_use_Failed() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(mySQLDriver);
        DeleteProductInput input = new DeleteProductInput();
        DeleteProductOutput output = new DeleteProductOutput();

        input.setId(100);

        deleteProductUseCase.execute(input, output);

        assertFalse(output.getWorkCheck());
    }
}
