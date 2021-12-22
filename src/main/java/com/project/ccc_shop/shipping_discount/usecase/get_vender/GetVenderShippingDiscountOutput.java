package com.project.ccc_shop.shipping_discount.usecase.get_vender;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.shipping_discount.entity.ShippingDiscount;

import java.util.ArrayList;
import java.util.List;

public class GetVenderShippingDiscountOutput extends Output {
    private List<ShippingDiscount> shippingDiscountList = new ArrayList<>();

    public List<ShippingDiscount> getShippingDiscountList() {
        return shippingDiscountList;
    }

    public void setShippingDiscountList(List<ShippingDiscount> shippingDiscountList) {
        this.shippingDiscountList = shippingDiscountList;
    }
}
