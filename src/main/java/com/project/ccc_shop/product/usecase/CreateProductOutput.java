package com.project.ccc_shop.product.usecase;

import com.project.ccc_shop.common.Output;

import java.util.UUID;

public class CreateProductOutput implements Output {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

}
