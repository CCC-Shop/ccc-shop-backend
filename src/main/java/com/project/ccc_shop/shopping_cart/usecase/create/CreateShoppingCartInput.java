package com.project.ccc_shop.shopping_cart.usecase.create;

import com.project.ccc_shop.common.Input;
import com.project.ccc_shop.product.entity.Category;

public class CreateShoppingCartInput implements Input {
    private int productId;
    private int customerId;
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
