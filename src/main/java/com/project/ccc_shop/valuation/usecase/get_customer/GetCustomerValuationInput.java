package com.project.ccc_shop.valuation.usecase.get_customer;

public class GetCustomerValuationInput {
    private int customerId;
    private int productId;

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
}
