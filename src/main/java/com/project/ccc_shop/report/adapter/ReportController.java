package com.project.ccc_shop.report.adapter;

import com.project.ccc_shop.report.usecase.GetSalesReportInput;
import com.project.ccc_shop.report.usecase.GetSalesReportOutput;
import com.project.ccc_shop.report.usecase.GetSalesReportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReportController {

    GetSalesReportUseCase getSalesReportUseCase;

    @Autowired
    public void setGetSalesReportUseCase(GetSalesReportUseCase getSalesReportUseCase) {
        this.getSalesReportUseCase = getSalesReportUseCase;
    }

    @PostMapping(value = "/get-sale-report")
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