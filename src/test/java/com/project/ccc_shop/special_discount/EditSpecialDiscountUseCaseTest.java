package com.project.ccc_shop.special_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;
import com.project.ccc_shop.special_discount.usecase.edit.EditSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.edit.EditSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.edit.EditSpecialDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditSpecialDiscountUseCaseTest {
    @Test
    public void edit_special_discount_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        EditSpecialDiscountUseCase editSpecialDiscountUseCase = new EditSpecialDiscountUseCase(mySQLDriver);
        EditSpecialDiscountInput input = new EditSpecialDiscountInput();
        EditSpecialDiscountOutput output = new EditSpecialDiscountOutput();

        input.setDiscountCode(1);
        input.setVenderId(4);
        input.setPolicyDescription("商城活動:筆電清倉大拍賣");
        Timestamp startTime = Timestamp.from(Instant.now());
        input.setStartTime(startTime);
        Timestamp endTime = Timestamp.from(Instant.now());
        input.setStartTime(endTime);
        input.setCategory(Category.TABLET);
        input.setDiscountRate(0.8);

        editSpecialDiscountUseCase.execute(input, output);

        assertNotNull(output);
    }
}