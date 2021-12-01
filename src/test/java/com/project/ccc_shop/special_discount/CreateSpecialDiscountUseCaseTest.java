package com.project.ccc_shop.special_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.special_discount.usecase.CreateSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.CreateSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.CreateSpecialDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateSpecialDiscountUseCaseTest {
    @Test
    public void create_special_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateSpecialDiscountUseCase createSpecialDiscountUseCase = new CreateSpecialDiscountUseCase(mySQLDriver);
        CreateSpecialDiscountInput input = new CreateSpecialDiscountInput();
        CreateSpecialDiscountOutput output = new CreateSpecialDiscountOutput();

        input.setProductId(123);
        input.setVenderId(123);
        input.setPolicyDescription("discount policy description test");
        input.setStartTime(Timestamp.valueOf("2021-11-23 00:00:00"));
        input.setEndTime(Timestamp.valueOf("2021-11-24 00:00:00"));
        // "2021-11-24T13:47:58.212+00:00"
        input.setCategory("computer");

        createSpecialDiscountUseCase.execute(input, output);

        assertNotNull(output.getDiscountCode());
//        assertEquals(6, output.getDiscountCode());
    }
}
