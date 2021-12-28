package com.project.ccc_shop.seasonings_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import com.project.ccc_shop.seasonings_discount.usecase.get_vender.GetVenderSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.get_vender.GetVenderSeasoningsDiscountOutput;
import com.project.ccc_shop.seasonings_discount.usecase.get_vender.GetVenderSeasoningsDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVenderSeasoningsDiscountUseCaseTest {
    @Test
    public void get_vender_seasonings_discount() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetVenderSeasoningsDiscountUseCase getVenderSeasoningsDiscountUseCase = new GetVenderSeasoningsDiscountUseCase(mySQLDriver);
        GetVenderSeasoningsDiscountInput input = new GetVenderSeasoningsDiscountInput();
        GetVenderSeasoningsDiscountOutput output = new GetVenderSeasoningsDiscountOutput();
        input.setVenderId(3);

        getVenderSeasoningsDiscountUseCase.execute(input, output);
        List<SeasoningsDiscount> seasoningsDiscountList = output.getSeasoningsDiscountList();

        assertEquals("Success!", output.getMessage());
        assertEquals(1, seasoningsDiscountList.size());

        SeasoningsDiscount discount = seasoningsDiscountList.get(0);
        assertEquals(2, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("春節特賣全商品享79折優惠", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-02-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-02-28T23:59:59Z")), discount.getEndTime());
        assertEquals(0.79, discount.getDiscountRate());
    }
}