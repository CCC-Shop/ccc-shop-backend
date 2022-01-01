package com.project.ccc_shop.report.usecase.SalesReport;

import com.project.ccc_shop.common.Output;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GetSalesReportOutput extends Output {
    private List<String> timeList = new ArrayList<>();
    private List<Integer> totalPriceList = new ArrayList<>();

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    public List<Integer> getTotalPriceList() {
        return totalPriceList;
    }

    public void setTotalPriceList(List<Integer> totalPriceList) {
        this.totalPriceList = totalPriceList;
    }
}
