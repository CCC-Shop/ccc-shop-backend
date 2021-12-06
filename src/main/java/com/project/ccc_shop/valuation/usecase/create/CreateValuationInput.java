package com.project.ccc_shop.valuation.usecase.create;

import com.project.ccc_shop.common.Input;

public class CreateValuationInput implements Input {
    private int customerId;
    private int productId;
    private String comment;
    private int rating;

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
