package com.project.ccc_shop.seasonings_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import com.project.ccc_shop.seasonings_discount.usecase.edit.EditSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.edit.EditSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.edit.EditSeasoningsDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditSeasoningsDiscountUseCaseTest {
    @Test
    public void edit_seasonings_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        EditSeasoningsDiscountUseCase editSeasoningsDiscountUseCase = new EditSeasoningsDiscountUseCase(mySQLDriver);
        EditSeasoningsDiscountInput input = new EditSeasoningsDiscountInput();
        EditSeasoningsDiscountOutput output = new EditSeasoningsDiscountOutput();

        input.setDiscountCode(1);
        input.setVenderId(4);
        input.setPolicyDescription("event celebration!");
        Timestamp startTime = Timestamp.from(Instant.now());
        input.setStartTime(startTime);
        Timestamp endTime = Timestamp.from(Instant.now());
        input.setStartTime(endTime);
        input.setDiscountRate(0.8);

        editSeasoningsDiscountUseCase.execute(input, output);

        assertNotNull(output);
    }
}