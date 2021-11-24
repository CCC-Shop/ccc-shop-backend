package com.project.ccc_shop.discount.entity;

import com.project.ccc_shop.product.entity.Category;

import java.sql.Timestamp;

public class Discount {
    private String discountCode;
    private String policyDescription;
    private Timestamp startTime;
    private Timestamp endTime;
    private double discountRate;
    private int targetPrice;
    private Category category;
}
