package com.project.ccc_shop.seasonings_discount.usecase.get_current;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class GetCurrentSeasoningsDiscountUseCase {
    private MySQLDriver mySQLDriver;

    public GetCurrentSeasoningsDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetCurrentSeasoningsDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<SeasoningsDiscount> discounts = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                            "FROM `seasonings_discount` " +
                            "WHERE ? > `start_time` " +
                            "AND ? < `end_time`;");

            Timestamp currentTime = Timestamp.from(Instant.now());
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));  // set timezone
            stmt.setTimestamp(1, currentTime, calendar);
            stmt.setTimestamp(2, currentTime, calendar);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SeasoningsDiscount seasoningsDiscount = new SeasoningsDiscount();
                    seasoningsDiscount.setDiscountCode(rs.getInt("discount_code"));
                    seasoningsDiscount.setVenderId(rs.getInt("vender_id"));
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
}
