package com.project.ccc_shop.shopping_cart.usecase.delete;

import com.project.ccc_shop.common.Output;

public class DeleteShoppingCartItemOutput extends Output {

    private boolean workCheck;

    public void setWorkCheck(boolean workCheck) {
        this.workCheck = workCheck;
    }

    public boolean getWorkCheck() {
        return workCheck;
    }

}
