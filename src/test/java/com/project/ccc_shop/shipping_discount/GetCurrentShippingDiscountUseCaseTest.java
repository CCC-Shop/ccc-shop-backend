package com.project.ccc_shop.shipping_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.shipping_discount.entity.ShippingDiscount;
import com.project.ccc_shop.shipping_discount.usecase.get_current.GetCurrentShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.get_current.GetCurrentShippingDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentShippingDiscountUseCaseTest {
    @Test
    public void get_all_current_shipping_discount() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetCurrentShippingDiscountUseCase getCurrentShippingDiscountUseCase = new GetCurrentShippingDiscountUseCase(mySQLDriver);
        GetCurrentShippingDiscountOutput output = new GetCurrentShippingDiscountOutput();

        getCurrentShippingDiscountUseCase.execute(output);
        List<ShippingDiscount> shippingDiscountList = output.getShippingDiscountList();

        assertEquals("Success!", output.getMessage());
        assertEquals(3, shippingDiscountList.size());

        ShippingDiscount discount = shippingDiscountList.get(0);
        assertEquals(1, discount.getDiscountCode());
        assertEquals(2, discount.getVenderId());
        assertEquals("2021/12/18當日結帳金額超過10000免運費", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-12-15T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-18T23:59:59Z")), discount.getEndTime());
        assertEquals(10000, discount.getTargetPrice());

        discount = shippingDiscountList.get(1);
        assertEquals(2, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("雙十一結帳金額超過15000免運", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-11-07T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-18T23:59:59Z")), discount.getEndTime());
        assertEquals(15000, discount.getTargetPrice());

        discount = shippingDiscountList.get(2);
        assertEquals(3, discount.getDiscountCode());
        assertEquals(4, discount.getVenderId());
        assertEquals("聖誕節結帳金額超過30000運費折抵", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-12-08T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-31T23:59:59Z")), discount.getEndTime());
        assertEquals(30000, discount.getTargetPrice());
    }
}