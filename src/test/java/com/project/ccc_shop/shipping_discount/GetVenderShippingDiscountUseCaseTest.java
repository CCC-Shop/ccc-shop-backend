package com.project.ccc_shop.shipping_discount;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.shipping_discount.entity.ShippingDiscount;
import com.project.ccc_shop.shipping_discount.usecase.get_vender.GetVenderShippingDiscountInput;
import com.project.ccc_shop.shipping_discount.usecase.get_vender.GetVenderShippingDiscountOutput;
import com.project.ccc_shop.shipping_discount.usecase.get_vender.GetVenderShippingDiscountUseCase;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVenderShippingDiscountUseCaseTest {
    @Test
    public void get_vender_shipping_discount() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetVenderShippingDiscountUseCase getVenderShippingDiscountUseCase = new GetVenderShippingDiscountUseCase(mySQLDriver);
        GetVenderShippingDiscountInput input = new GetVenderShippingDiscountInput();
        GetVenderShippingDiscountOutput output = new GetVenderShippingDiscountOutput();
        input.setVenderId(3);

        getVenderShippingDiscountUseCase.execute(input, output);
        List<ShippingDiscount> shippingDiscountList = output.getShippingDiscountList();

        assertEquals("Success!", output.getMessage());
        assertEquals(2, shippingDiscountList.size());

        ShippingDiscount discount = shippingDiscountList.get(0);
        assertEquals(2, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("雙十一結帳金額超過15000免運", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-11-07T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-12-18T23:59:59Z")), discount.getEndTime());
        assertEquals(15000, discount.getTargetPrice());

        discount = shippingDiscountList.get(1);
        assertEquals(4, discount.getDiscountCode());
        assertEquals(3, discount.getVenderId());
        assertEquals("No shipping fee for all products in ASUS if you order more than NTD35000", discount.getPolicyDescription());
        assertEquals(Timestamp.from(Instant.parse("2021-10-01T00:00:00Z")), discount.getStartTime());
        assertEquals(Timestamp.from(Instant.parse("2021-10-31T00:00:00Z")), discount.getEndTime());
        assertEquals(35000, discount.getTargetPrice());
    }
}