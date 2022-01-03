package com.project.ccc_shop.seasonings_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentSeasoningsDiscountUseCaseTest {
    @Test
    public void get_all_current_seasonings_discount() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetCurrentSeasoningsDiscountUseCase getCurrentSeasoningsDiscountUseCase = new GetCurrentSeasoningsDiscountUseCase(mySQLDriver);
        GetCurrentSeasoningsDiscountOutput output = new GetCurrentSeasoningsDiscountOutput();

        getCurrentSeasoningsDiscountUseCase.execute(output);
        List<SeasoningsDiscount> seasoningsDiscountList = output.getSeasoningsDiscountList();

        assertEquals("Success!", output.getMessage());
        assertEquals(2, seasoningsDiscountList.size());

        SeasoningsDiscount discount = seasoningsDiscountList.get(0);
        assertEquals(5, discount.getDiscountCode());
        assertEquals(4, discount.getVenderId());
        assertEquals("Samsung", discount.getVenderName());
        assertEquals("新年買起來！虎年1月Samsung全系列商品85折優惠！", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2022-01-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2022-01-31T23:59:59Z")), discount.getEndTime());
        assertEquals(0.85, discount.getDiscountRate());

        discount = seasoningsDiscountList.get(1);
        assertEquals(6, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("ASUS", discount.getVenderName());
        assertEquals("ASUS全館7折優惠！", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-12-29T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2022-01-14T23:59:59Z")), discount.getEndTime());
        assertEquals(0.7, discount.getDiscountRate());
    }
}