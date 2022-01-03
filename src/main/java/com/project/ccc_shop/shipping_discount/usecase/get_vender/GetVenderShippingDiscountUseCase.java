package com.project.ccc_shop.shipping_discount.usecase.get_vender;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountUseCase;
import com.project.ccc_shop.shipping_discount.entity.ShippingDiscount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetVenderShippingDiscountUseCase {
    private MySQLDriver mySQLDriver;

    public GetVenderShippingDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetVenderShippingDiscountInput input, GetVenderShippingDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<ShippingDiscount> discounts = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                            "FROM `shipping_discount` " +
                            "WHERE `vender_id` = ?;");

            stmt.setInt(1, input.getVenderId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ShippingDiscount shippingDiscount = new ShippingDiscount();
                    shippingDiscount.setDiscountCode(rs.getInt("discount_code"));
                    shippingDiscount.setVenderId(rs.getInt("vender_id"));
                    shippingDiscount.setVenderName(GetCurrentSeasoningsDiscountUseCase.queryVenderName(connection, rs.getInt("vender_id")));
                    shippingDiscount.setPolicyDescription(rs.getString("policy_description"));
                    shippingDiscount.setStartTime(rs.getTimestamp("start_time"));
                    shippingDiscount.setEndTime(rs.getTimestamp("end_time"));
                    shippingDiscount.setTargetPrice(rs.getInt("target_price"));

                    discounts.add(shippingDiscount);
                }
            }

            output.setShippingDiscountList(discounts);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
