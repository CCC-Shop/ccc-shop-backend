package com.project.ccc_shop.valuation.entity;

public class Valuation {
    private int customerId;
    private String comment;
    private int rating;


    public Valuation(int customerId, String comment, int rating) {
        this.customerId = customerId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
