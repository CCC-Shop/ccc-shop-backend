package com.project.ccc_shop.report.adapter;

import com.project.ccc_shop.report.usecase.SalesReport.GetSalesReportInput;
import com.project.ccc_shop.report.usecase.SalesReport.GetSalesReportOutput;
import com.project.ccc_shop.report.usecase.SalesReport.GetSalesReportUseCase;
import com.project.ccc_shop.report.usecase.productSalesReport.GetProductSalesReportInput;
import com.project.ccc_shop.report.usecase.productSalesReport.GetProductSalesReportOutput;
import com.project.ccc_shop.report.usecase.productSalesReport.GetProductSalesReportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReportController {

    GetProductSalesReportUseCase getProductSalesReportUseCase;
    GetSalesReportUseCase getSalesReportUseCase;

    @Autowired
    public void setGetProductSalesReportUseCase(GetProductSalesReportUseCase getProductSalesReportUseCase) {
        this.getProductSalesReportUseCase = getProductSalesReportUseCase;
    }

    @Autowired
    public void setGetSalesReportUseCase(GetSalesReportUseCase getSalesReportUseCase) {
        this.getSalesReportUseCase = getSalesReportUseCase;
    }

    @PostMapping(value = "/get-product-sales-report")
    public ResponseEntity<GetProductSalesReportOutput> GetProductSalesReportOutput(@RequestBody GetProductSalesReportInput requestBody) {
        GetProductSalesReportOutput output = new GetProductSalesReportOutput();

        try {
            this.getProductSalesReportUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/get-sales-report")
    public ResponseEntity<GetSalesReportOutput> GetSalesReportOutput(@RequestBody GetSalesReportInput requestBody) {
        GetSalesReportOutput output = new GetSalesReportOutput();

        try {
            this.getSalesReportUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}