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
        assertEquals(3, seasoningsDiscountList.size());

        SeasoningsDiscount discount = seasoningsDiscountList.get(0);
        assertEquals(1, discount.getDiscountCode());
        assertEquals(2, discount.getVenderId());
        assertEquals("開學季全面9折", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2020-08-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-30T23:59:59Z")), discount.getEndTime());
        assertEquals(0.9, discount.getDiscountRate());

        discount = seasoningsDiscountList.get(1);
        assertEquals(2, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("春節特賣全商品享79折優惠", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-02-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-28T23:59:59Z")), discount.getEndTime());
        assertEquals(0.79, discount.getDiscountRate());

        discount = seasoningsDiscountList.get(2);
        assertEquals(3, discount.getDiscountCode());
        assertEquals(4, discount.getVenderId());
        assertEquals("母親節活動全店88折", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-05-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-31T23:59:59Z")), discount.getEndTime());
        assertEquals(0.88, discount.getDiscountRate());
    }
}