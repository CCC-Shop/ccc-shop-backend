package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.usecase.delete.DeleteProductInput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductOutput;
import com.project.ccc_shop.product.usecase.delete.DeleteProductUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteProductUseCaseTest {
    @Test
    public void delete_product_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(mySQLDriver);
        DeleteProductInput input = new DeleteProductInput();
        DeleteProductOutput output = new DeleteProductOutput();

        input.setId(1);

        deleteProductUseCase.execute(input, output);

        assertNotNull(output.check());
    }
}
