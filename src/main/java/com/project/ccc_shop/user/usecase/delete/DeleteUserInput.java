package com.project.ccc_shop.user.usecase.delete;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.product.entity.Category;

public class DeleteUserInput implements Input{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
