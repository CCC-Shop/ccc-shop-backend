package com.project.ccc_shop.product.usecase.delete;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.product.entity.Category;

public class DeleteProductInput implements Input {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
