package com.project.ccc_shop.shipping_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import com.project.ccc_shop.shipping_discount.usecase.edit.EditShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.edit.EditShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.edit.EditShippingDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditShippingDiscountUseCaseTest {
    @Test
    public void edit_shipping_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        EditShippingDiscountUseCase editSeasoningsDiscountUseCase = new EditShippingDiscountUseCase(mySQLDriver);
        EditShippingDiscountInput input = new EditShippingDiscountInput();
        EditShippingDiscountOutput output = new EditShippingDiscountOutput();

        input.setDiscountCode(1);
        input.setVenderId(4);
        input.setPolicyDescription("1111優惠");
        Timestamp startTime = Timestamp.from(Instant.now());
        input.setStartTime(startTime);
        Timestamp endTime = Timestamp.from(Instant.now());
        input.setStartTime(endTime);
        input.setTargetPrice(0);

        editSeasoningsDiscountUseCase.execute(input, output);

        assertNotNull(output);
    }
}