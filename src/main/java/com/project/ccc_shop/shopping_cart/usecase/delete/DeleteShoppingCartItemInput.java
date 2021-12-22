package com.project.ccc_shop.shopping_cart.usecase.delete;

import com.project.ccc_shop.common.Input;

public class DeleteShoppingCartItemInput implements Input {
    private int productId;
    private int customerId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
