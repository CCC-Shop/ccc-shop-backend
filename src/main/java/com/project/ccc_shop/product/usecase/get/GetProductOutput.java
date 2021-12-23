package com.project.ccc_shop.product.usecase.get;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.product.entity.Category;

public class GetProductOutput extends Output {

    private int venderId;
    private String name;
    private Category category;
    private int price;
    private int stock;
    private String warehouseAddress;
    private String description;
    private String pictureURL;
    private boolean existFlag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public boolean getExistFlag() {
        return existFlag;
    }

    public void setExistFlag(boolean existFlag) {
        this.existFlag = existFlag;
    }
}
