package com.project.ccc_shop.report.usecase.productSalesReport;

import com.project.ccc_shop.common.Input;

import java.sql.Timestamp;

public class GetProductSalesReportInput implements Input {
    private int venderId;
    private Timestamp startTime;
    private Timestamp endTime;

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
