package com.project.ccc_shop.seasonings_discount.usecase.get_vender;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetVenderSeasoningsDiscountUseCase {
    private MySQLDriver mySQLDriver;

    public GetVenderSeasoningsDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetVenderSeasoningsDiscountInput input, GetVenderSeasoningsDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<SeasoningsDiscount> discounts = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                            "FROM `seasonings_discount` " +
                            "WHERE `vender_id` = ?;");

            stmt.setInt(1, input.getVenderId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SeasoningsDiscount seasoningsDiscount = new SeasoningsDiscount();
                    seasoningsDiscount.setDiscountCode(rs.getInt("discount_code"));
                    seasoningsDiscount.setVenderId(rs.getInt("vender_id"));
                    seasoningsDiscount.setVenderName(queryVenderName(connection, rs.getInt("vender_id")));
                    seasoningsDiscount.setPolicyDescription(rs.getString("policy_description"));
                    seasoningsDiscount.setStartTime(rs.getTimestamp("start_time"));
                    seasoningsDiscount.setEndTime(rs.getTimestamp("end_time"));
                    seasoningsDiscount.setDiscountRate(rs.getDouble("discount_rate"));

                    discounts.add(seasoningsDiscount);
                }
            }

            output.setSeasoningsDiscountList(discounts);
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
