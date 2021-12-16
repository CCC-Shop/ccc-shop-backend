package com.project.ccc_shop.special_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.create.CreateSpecialDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSpecialDiscountUseCaseTest {
    @Test
    public void create_special_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        CreateSpecialDiscountUseCase createSpecialDiscountUseCase = new CreateSpecialDiscountUseCase(mySQLDriver);
        CreateSpecialDiscountInput input = new CreateSpecialDiscountInput();
        CreateSpecialDiscountOutput output = new CreateSpecialDiscountOutput();
        input.setVenderId(2);
        input.setPolicyDescription("IMac is on sale! 25% off!");
        input.setStartTime(Timestamp.from(Instant.parse("2021-11-23T00:00:00Z")));
        input.setEndTime(Timestamp.from(Instant.parse("2021-11-24T00:00:00Z")));
        input.setCategory(Category.COMPUTER);
        input.setDiscountRate(0.75);

        createSpecialDiscountUseCase.execute(input, output);

        assertEquals("Success!", output.getMessage());
    }
}
