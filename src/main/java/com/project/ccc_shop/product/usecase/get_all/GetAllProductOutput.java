package com.project.ccc_shop.product.usecase.get_all;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.product.entity.Product;

import java.util.List;

public class GetAllProductOutput implements Output {

    private List<Product> productList;

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

}
