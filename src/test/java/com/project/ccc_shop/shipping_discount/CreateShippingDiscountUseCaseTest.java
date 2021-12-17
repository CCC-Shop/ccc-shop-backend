package com.project.ccc_shop.shipping_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.create.CreateShippingDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateShippingDiscountUseCaseTest {
    @Test
    public void create_shipping_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        CreateShippingDiscountUseCase createShippingDiscountUseCase = new CreateShippingDiscountUseCase(mySQLDriver);
        CreateShippingDiscountInput input = new CreateShippingDiscountInput();
        CreateShippingDiscountOutput output = new CreateShippingDiscountOutput();
        input.setVenderId(3);
        input.setPolicyDescription("No shipping fee for all products in ASUS if you order more than NTD35000");
        input.setStartTime(Timestamp.from(Instant.parse("2021-10-01T00:00:00Z")));
        input.setEndTime(Timestamp.from(Instant.parse("2021-10-31T00:00:00Z")));
        input.setTargetPrice(35000);

        createShippingDiscountUseCase.execute(input, output);

        assertEquals("Success!", output.getMessage());
    }
}
