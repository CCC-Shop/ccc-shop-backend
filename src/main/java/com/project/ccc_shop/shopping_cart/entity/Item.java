package com.project.ccc_shop.shopping_cart.entity;

public class Item {

    private int id;
    private int venderId;
    private String name;
    private String category;
    private int price;
    private int stock;
    private String warehouseAddress;
    private String description;
    private String pictureURL;
    private String venderName;
    private int quantity;

    public Item(int id, int venderId, String name, String category, int price, int stock, String warehouseAddress, String description, String pictureURL, String venderName, int quantity) {
        this.id = id;
        this.venderId = venderId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.warehouseAddress = warehouseAddress;
        this.description = description;
        this.pictureURL = pictureURL;
        this.venderName = venderName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
