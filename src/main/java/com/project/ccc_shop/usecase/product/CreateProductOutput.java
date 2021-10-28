package com.project.ccc_shop.usecase.product;

import com.project.ccc_shop.usecase.Output;

public class CreateProductOutput implements Output {
    public static CreateProductOutput newInstance(){
        return new CreateProductOutput();
    }

    @Override
    public Output setId(String id) {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}
