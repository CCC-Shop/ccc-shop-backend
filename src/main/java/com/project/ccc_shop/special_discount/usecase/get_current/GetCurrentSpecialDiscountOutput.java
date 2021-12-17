package com.project.ccc_shop.special_discount.usecase.get_current;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;

import java.util.ArrayList;
import java.util.List;

public class GetCurrentSpecialDiscountOutput extends Output {
    private List<SpecialDiscount> specialDiscountList = new ArrayList<>();

    public List<SpecialDiscount> getSpecialDiscountList() {
        return specialDiscountList;
    }

    public void setSpecialDiscountList(List<SpecialDiscount> specialDiscountList) {
        this.specialDiscountList = specialDiscountList;
    }
}
