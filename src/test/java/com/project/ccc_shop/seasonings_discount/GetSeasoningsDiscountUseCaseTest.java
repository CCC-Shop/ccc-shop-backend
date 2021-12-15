package com.project.ccc_shop.seasonings_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.usecase.get.GetSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.get.GetSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.get.GetSeasoningsDiscountUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetSeasoningsDiscountUseCaseTest {
    @Test
    public void get_seasonings_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        GetSeasoningsDiscountUseCase getSeasoningsDiscountUseCase = new GetSeasoningsDiscountUseCase(mySQLDriver);
        GetSeasoningsDiscountInput input = new GetSeasoningsDiscountInput();
        GetSeasoningsDiscountOutput output = new GetSeasoningsDiscountOutput();

        input.setDiscountCode(1);

        getSeasoningsDiscountUseCase.execute(input, output);

        assertNotNull(output.getDiscountRate());

        assertEquals(2, output.getVenderId());
        assertEquals("using for xxx", output.getPolicyDescription());
        assertEquals("2021-01-01 08:00:00.0", output.getStartTime());
        assertEquals("2022-01-01 07:59:59.0", output.getEndTime());
        assertEquals(0.5, output.getDiscountRate());
    }

}
