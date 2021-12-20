package com.project.ccc_shop.valuation.usecase.get;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.valuation.entity.Valuation;

import java.util.List;

public class GetValuationOutput extends Output {
    private List<Valuation> valuationList;

    public List<Valuation> getValuationList() {
        return valuationList;
    }

    public void setValuationList(List<Valuation> valuationList) {
        this.valuationList = valuationList;
    }
}
