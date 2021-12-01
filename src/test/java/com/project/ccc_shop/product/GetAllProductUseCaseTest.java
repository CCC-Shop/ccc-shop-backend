package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Product;
import com.project.ccc_shop.product.usecase.get.GetProductInput;
import com.project.ccc_shop.product.usecase.get.GetProductOutput;
import com.project.ccc_shop.product.usecase.get.GetProductUseCase;
import com.project.ccc_shop.product.usecase.get_all.GetAllProductOutput;
import com.project.ccc_shop.product.usecase.get_all.GetAllProductUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetAllProductUseCaseTest {
    @Test
    public void get_all_product_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        GetAllProductUseCase getAllProductUseCase = new GetAllProductUseCase(mySQLDriver);
        GetAllProductOutput output = new GetAllProductOutput();

        getAllProductUseCase.execute(output);
        List<Product> testProductList = output.getProductList();

        assertNotNull(testProductList.get(3).getVenderId());
        assertEquals(1, testProductList.get(3).getVenderId());
        assertEquals("ipad", testProductList.get(3).getName());
        assertEquals("TABLET", testProductList.get(3).getCategory());
        assertEquals(20000, testProductList.get(3).getPrice());
        assertEquals(3, testProductList.get(3).getStock());
        assertEquals("address", testProductList.get(3).getWarehouseAddress());
        assertEquals("great product in your life", testProductList.get(3).getDescription());
        assertEquals("https://picsum.photos/200", testProductList.get(3).getPictureURL());
    }

}
