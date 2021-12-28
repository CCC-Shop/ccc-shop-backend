package com.project.ccc_shop.special_discount.usecase.get_vender;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;

import java.util.ArrayList;
import java.util.List;

public class GetVenderSpecialDiscountOutput extends Output {
    private List<SpecialDiscount> specialDiscountArrayList = new ArrayList<>();

    public List<SpecialDiscount> getSpecialDiscountList() {
        return specialDiscountArrayList;
    }

    public void setSpecialDiscountList(List<SpecialDiscount> specialDiscountList) {
        this.specialDiscountArrayList = specialDiscountList;
    }
}
