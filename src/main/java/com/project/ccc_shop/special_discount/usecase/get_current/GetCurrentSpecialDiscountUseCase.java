package com.project.ccc_shop.special_discount.usecase.get_current;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class GetCurrentSpecialDiscountUseCase {
    private MySQLDriver mySQLDriver;

    public GetCurrentSpecialDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetCurrentSpecialDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<SpecialDiscount> discounts = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                            "FROM `special_discount` " +
                            "WHERE ? > `start_time` " +
                            "AND ? < `end_time`;");

            Timestamp currentTime = Timestamp.from(Instant.now());
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));  // set timezone
            stmt.setTimestamp(1, currentTime, calendar);
            stmt.setTimestamp(2, currentTime, calendar);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SpecialDiscount specialDiscount = new SpecialDiscount();
                    specialDiscount.setDiscountCode(rs.getInt("discount_code"));
                    specialDiscount.setVenderId(rs.getInt("vender_id"));
                    specialDiscount.setVenderName(queryVenderName(connection, rs.getInt("vender_id")));
                    specialDiscount.setPolicyDescription(rs.getString("policy_description"));
                    specialDiscount.setStartTime(rs.getTimestamp("start_time"));
                    specialDiscount.setEndTime(rs.getTimestamp("end_time"));
                    specialDiscount.setCategory(Category.valueOf(rs.getString("category")));
                    specialDiscount.setDiscountRate(rs.getDouble("discount_rate"));

                    discounts.add(specialDiscount);
                }
            }

            output.setSpecialDiscountList(discounts);
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
