package com.project.ccc_shop.special_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;
import com.project.ccc_shop.special_discount.usecase.get_vender.GetVenderSpecialDiscountInput;
import com.project.ccc_shop.special_discount.usecase.get_vender.GetVenderSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.get_vender.GetVenderSpecialDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVenderSpecialDiscountUseCaseTest {
    @Test
    public void get_vender_seasonings_discount() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetVenderSpecialDiscountUseCase getVenderSpecialDiscountUseCase = new GetVenderSpecialDiscountUseCase(mySQLDriver);
        GetVenderSpecialDiscountInput input = new GetVenderSpecialDiscountInput();
        GetVenderSpecialDiscountOutput output = new GetVenderSpecialDiscountOutput();
        input.setVenderId(3);

        getVenderSpecialDiscountUseCase.execute(input, output);
        List<SpecialDiscount> specialDiscountList = output.getSpecialDiscountList();

        assertEquals("Success!", output.getMessage());
        assertEquals(1, specialDiscountList.size());

        SpecialDiscount discount = specialDiscountList.get(0);
        assertEquals(2, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("TUF Dash F15折扣", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-02-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-02-28T23:59:59Z")), discount.getEndTime());
        assertEquals(Category.NOTEBOOK, discount.getCategory());
        assertEquals(0.8, discount.getDiscountRate());
    }
}