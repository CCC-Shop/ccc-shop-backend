package com.project.ccc_shop.shipping_discount.usecase.get_current;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.shipping_discount.entity.ShippingDiscount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class GetCurrentShippingDiscountUseCase {
    private MySQLDriver mySQLDriver;

    public GetCurrentShippingDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetCurrentShippingDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<ShippingDiscount> discounts = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                            "FROM `shipping_discount` " +
                            "WHERE ? > `start_time` " +
                            "AND ? < `end_time`;");

            Timestamp currentTime = Timestamp.from(Instant.now());
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));  // set timezone
            stmt.setTimestamp(1, currentTime, calendar);
            stmt.setTimestamp(2, currentTime, calendar);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ShippingDiscount shippingDiscount = new ShippingDiscount();
                    shippingDiscount.setDiscountCode(rs.getInt("discount_code"));
                    shippingDiscount.setVenderId(rs.getInt("vender_id"));
                    shippingDiscount.setVenderName(queryVenderName(connection, rs.getInt("vender_id")));
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

    private String queryVenderName(Connection connection, int venderId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT `username` FROM `user` WHERE `id` = ?")) {
            stmt.setInt(1, venderId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        throw new RuntimeException("user not found");
    }
}
