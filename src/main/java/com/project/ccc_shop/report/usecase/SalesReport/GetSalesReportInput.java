package com.project.ccc_shop.report.usecase.SalesReport;

import com.project.ccc_shop.common.Input;

import java.sql.Timestamp;

public class GetSalesReportInput implements Input {
    private int venderId;

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }
}
