package com.project.ccc_shop.seasonings_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.create.CreateSeasoningsDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSeasoningsDiscountUseCaseTest {
    @Test
    public void create_seasonings_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase = new CreateSeasoningsDiscountUseCase(mySQLDriver);
        CreateSeasoningsDiscountInput input = new CreateSeasoningsDiscountInput();
        CreateSeasoningsDiscountOutput output = new CreateSeasoningsDiscountOutput();
        input.setVenderId(3);
        input.setPolicyDescription("All products in ASUS are 50% off on Christmas Day!");
        input.setStartTime(Timestamp.from(Instant.parse("2021-12-25T00:00:00Z")));
        input.setEndTime(Timestamp.from(Instant.parse("2021-12-25T23:59:00Z")));
        input.setDiscountRate(0.5);

        createSeasoningsDiscountUseCase.execute(input, output);

        assertEquals("Success!", output.getMessage());
    }
}
