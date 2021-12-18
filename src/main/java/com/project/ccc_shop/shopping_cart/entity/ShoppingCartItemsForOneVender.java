package com.project.ccc_shop.shopping_cart.entity;

import com.project.ccc_shop.product.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemsForOneVender {

    private String venderName;
    private List<Item> items;

    public ShoppingCartItemsForOneVender(String venderName, List<Item> items) {
        this.venderName = venderName;
        this.items = items;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
            this.items = items;
        }

}

