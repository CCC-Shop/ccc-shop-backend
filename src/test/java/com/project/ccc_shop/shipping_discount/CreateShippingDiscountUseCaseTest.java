package com.project.ccc_shop.shipping_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.shipping_discount.usecase.CreateShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.CreateShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.CreateShippingDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateShippingDiscountUseCaseTest {
    @Test
    public void create_shipping_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateShippingDiscountUseCase createShippingDiscountUseCase = new CreateShippingDiscountUseCase(mySQLDriver);
        CreateShippingDiscountInput input = new CreateShippingDiscountInput();
        CreateShippingDiscountOutput output = new CreateShippingDiscountOutput();

//        input.setDiscountCode("50percentOff");
        input.setOrderId(123);
        input.setUserId(123);
        input.setPolicyDescription("discount policy description test");
        input.setStartTime(Timestamp.valueOf("2021-11-23 00:00:00"));
        input.setEndTime(Timestamp.valueOf("2021-11-24 00:00:00"));
        // "2021-11-24T13:47:58.212+00:00"
        input.setTargetPrice(1000);

        createShippingDiscountUseCase.execute(input, output);

        assertNotNull(output.getDiscountCode());
//        assertEquals(6, output.getDiscountCode());
    }
}
