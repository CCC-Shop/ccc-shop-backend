package com.project.ccc_shop.seasonings_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.usecase.CreateSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.CreateSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.CreateSeasoningsDiscountUseCase;
import com.project.ccc_shop.product.entity.Category;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSeasoningsDiscountUseCaseTest {
    @Test
    public void create_seasonings_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateSeasoningsDiscountUseCase createSeasoningsDiscountUseCase = new CreateSeasoningsDiscountUseCase(mySQLDriver);
        CreateSeasoningsDiscountInput input = new CreateSeasoningsDiscountInput();
        CreateSeasoningsDiscountOutput output = new CreateSeasoningsDiscountOutput();

//        input.setDiscountCode("50percentOff");
        input.setOrderId(123);
        input.setVenderId(123);
        input.setPolicyDescription("discount policy description test");
        input.setStartTime(Timestamp.valueOf("2021-11-23 00:00:00"));
        input.setEndTime(Timestamp.valueOf("2021-11-24 00:00:00"));
        // "2021-11-24T13:47:58.212+00:00"
        input.setDiscountRate(0.5);
//        input.setTargetPrice(100);
//        input.setCategory(Category.PHONE);

        createSeasoningsDiscountUseCase.execute(input, output);

        assertNotNull(output.getDiscountCode());
//        assertEquals(6, output.getDiscountCode());
    }
}
