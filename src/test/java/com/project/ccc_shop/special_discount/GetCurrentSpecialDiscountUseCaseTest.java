package com.project.ccc_shop.special_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;
import com.project.ccc_shop.special_discount.usecase.get_current.GetCurrentSpecialDiscountOutput;
import com.project.ccc_shop.special_discount.usecase.get_current.GetCurrentSpecialDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentSpecialDiscountUseCaseTest {
    @Test
    public void get_all_current_special_discount() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetCurrentSpecialDiscountUseCase getCurrentSpecialDiscountUseCase = new GetCurrentSpecialDiscountUseCase(mySQLDriver);
        GetCurrentSpecialDiscountOutput output = new GetCurrentSpecialDiscountOutput();

        getCurrentSpecialDiscountUseCase.execute(output);
        List<SpecialDiscount> specialDiscountList = output.getSpecialDiscountList();

        assertEquals("Success!", output.getMessage());
        assertEquals(3, specialDiscountList.size());

        SpecialDiscount discount = specialDiscountList.get(0);
        assertEquals(1, discount.getDiscountCode());
        assertEquals(2, discount.getVenderId());
        assertEquals("iPhone 13 Pro特惠出清", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-08-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-30T23:59:59Z")), discount.getEndTime());
        assertEquals(Category.PHONE, discount.getCategory());
        assertEquals(0.9, discount.getDiscountRate());

        discount = specialDiscountList.get(1);
        assertEquals(2, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("TUF Dash F15折扣", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-02-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-28T23:59:59Z")), discount.getEndTime());
        assertEquals(Category.NOTEBOOK, discount.getCategory());
        assertEquals(0.8, discount.getDiscountRate());

        discount = specialDiscountList.get(2);
        assertEquals(3, discount.getDiscountCode());
        assertEquals(4, discount.getVenderId());
        assertEquals("Galaxy Z Fold3 5G上市優惠", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-05-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-31T23:59:59Z")), discount.getEndTime());
        assertEquals(Category.TABLET, discount.getCategory());
        assertEquals(0.75, discount.getDiscountRate());
    }
}