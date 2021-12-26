package com.project.ccc_shop.valuation.entity;

public class Valuation {
    private String customerName;
    private String comment;
    private int rating;

    public Valuation(String customerName, String comment, int rating) {
        this.customerName = customerName;
        this.comment = comment;
        this.rating = rating;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
