package com.project.ccc_shop.valuation.usecase.get_customer;

import com.project.ccc_shop.common.Output;

public class GetCustomerValuationOutput extends Output {
    private String comment = "";
    private int rating;

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
