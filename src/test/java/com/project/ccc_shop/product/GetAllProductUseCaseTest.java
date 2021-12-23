package com.project.ccc_shop.product;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.product.entity.Product;
import com.project.ccc_shop.product.usecase.get_all.GetAllProductOutput;
import com.project.ccc_shop.product.usecase.get_all.GetAllProductUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllProductUseCaseTest {
    final static String SUCCESS_MESSAGE = "Success!";

    @Test
    public void get_all_product_succeeded() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetAllProductUseCase getAllProductUseCase = new GetAllProductUseCase(mySQLDriver);
        GetAllProductOutput output = new GetAllProductOutput();

        getAllProductUseCase.execute(output);

        assertEquals(SUCCESS_MESSAGE, output.getMessage());
        List<Product> productList = output.getProductList();
        assertEquals(15, productList.size());
        Product product = productList.get(2);
        assertEquals(3, product.getId());
        assertEquals(2, product.getVenderId());
        assertEquals("iPhone 13 Pro", product.getName());
        assertEquals(Category.PHONE, product.getCategory());
        assertEquals(32900, product.getPrice());
        assertEquals(200, product.getStock());
        assertEquals("XX市XX區XX路XX號XX樓", product.getWarehouseAddress());
        assertEquals("這是很貴的蘋果手機", product.getDescription());
        assertEquals("https://web-eshop.cdn.hinet.net/eShop%20Images/Product/phones/000100004254/154632-C50321583002.jpg", product.getPictureURL());
        assertTrue(product.getExistFlag());

        product = productList.get(8);
        assertEquals(9, product.getId());
        assertEquals(3, product.getVenderId());
        assertEquals("TUF Dash F15", product.getName());
        assertEquals(Category.NOTEBOOK, product.getCategory());
        assertEquals(45000, product.getPrice());
        assertEquals(326, product.getStock());
        assertEquals("XX市XX區XX路XX號XX樓", product.getWarehouseAddress());
        assertEquals("這是很好用的華碩筆電", product.getDescription());
        assertEquals("https://dlcdnwebimgs.asus.com/gain/15d0f62e-8b6b-4acd-b938-1bfbd908cb67/", product.getPictureURL());
        assertFalse(product.getExistFlag());
    }
}
