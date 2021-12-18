package com.project.ccc_shop.shopping_cart.usecase.get;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.product.entity.Product;
import com.project.ccc_shop.shopping_cart.entity.Item;
import com.project.ccc_shop.shopping_cart.entity.ShoppingCartItemsForOneVender;

import java.util.List;

public class GetShoppingCartItemsOutput extends Output {

    private List<ShoppingCartItemsForOneVender> shoppingCartItems;

    public List<ShoppingCartItemsForOneVender> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItemsForOneVender> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

}
