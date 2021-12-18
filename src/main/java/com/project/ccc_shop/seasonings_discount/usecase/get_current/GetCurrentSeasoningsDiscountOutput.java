package com.project.ccc_shop.seasonings_discount.usecase.get_current;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;

import java.util.ArrayList;
import java.util.List;

public class GetCurrentSeasoningsDiscountOutput extends Output {
    private List<SeasoningsDiscount> seasoningsDiscountList = new ArrayList<>();

    public List<SeasoningsDiscount> getSeasoningsDiscountList() {
        return seasoningsDiscountList;
    }

    public void setSeasoningsDiscountList(List<SeasoningsDiscount> seasoningsDiscountList) {
        this.seasoningsDiscountList = seasoningsDiscountList;
    }
}
