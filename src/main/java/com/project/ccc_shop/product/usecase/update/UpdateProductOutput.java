package com.project.ccc_shop.product.usecase.update;

import com.project.ccc_shop.common.Output;

public class UpdateProductOutput implements Output {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
