package com.project.ccc_shop.report.usecase;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;

import java.util.ArrayList;
import java.util.List;

public class GetSalesReportOutput extends Output {
    private List<String> categoryList = new ArrayList<>();
    private List<Integer> quantityList = new ArrayList<>();

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Integer> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(List<Integer> quantityList) {
        this.quantityList = quantityList;
    }
}
