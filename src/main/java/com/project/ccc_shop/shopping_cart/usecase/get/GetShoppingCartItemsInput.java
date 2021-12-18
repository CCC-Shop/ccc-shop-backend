package com.project.ccc_shop.shopping_cart.usecase.get;

import com.project.ccc_shop.common.Input;

public class GetShoppingCartItemsInput implements Input {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
