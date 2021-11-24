package com.project.ccc_shop.product.usecase.create;

import com.project.ccc_shop.common.Output;

import java.util.UUID;

public class CreateProductOutput implements Output {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
