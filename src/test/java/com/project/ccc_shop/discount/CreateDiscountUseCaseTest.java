package com.project.ccc_shop.discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.discount.usecase.CreateDiscountInput;
import com.project.ccc_shop.discount.usecase.CreateDiscountOutput;
import com.project.ccc_shop.discount.usecase.CreateDiscountUseCase;
import com.project.ccc_shop.product.entity.Category;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateDiscountUseCaseTest {
    @Test
    public void create_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateDiscountUseCase createDiscountUseCase = new CreateDiscountUseCase(mySQLDriver);
        CreateDiscountInput input = new CreateDiscountInput();
        CreateDiscountOutput output = new CreateDiscountOutput();

        input.setDiscountCode("abc");
        input.setPolicyDescription("discount policy description");
        input.setStartTime(Timestamp.valueOf("2021-11-23 00:00:00"));
        input.setEndTime(Timestamp.valueOf("2021-11-24 00:00:00"));
        input.setDiscountRate(0.5);
        input.setTargetPrice(100);
        input.setCategory(Category.PHONE);

        createDiscountUseCase.execute(input, output);

        assertNotNull(output.getDiscountCode());
        assertEquals("abc", output.getDiscountCode());
    }
}
