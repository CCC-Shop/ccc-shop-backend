package com.project.ccc_shop.valuation.entity;

public class Valuation {
    private int customerId;
    private int productId;
    private String comment;
    private int rating;

    public Valuation(int customerId, int productId, String comment, int rating) {
        this.customerId = customerId;
        this.productId = productId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
