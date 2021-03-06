package com.project.ccc_shop.seasonings_discount.usecase.get_vender;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountUseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class GetVenderSeasoningsDiscountUseCase implements UseCase<GetVenderSeasoningsDiscountInput, GetVenderSeasoningsDiscountOutput>{
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
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));  // set timezone
                    seasoningsDiscount.setDiscountCode(rs.getInt("discount_code"));
                    seasoningsDiscount.setVenderId(rs.getInt("vender_id"));
                    seasoningsDiscount.setVenderName(GetCurrentSeasoningsDiscountUseCase.queryVenderName(connection, rs.getInt("vender_id")));
                    seasoningsDiscount.setPolicyDescription(rs.getString("policy_description"));
                    seasoningsDiscount.setStartTime(rs.getTimestamp("start_time", calendar));
                    seasoningsDiscount.setEndTime(rs.getTimestamp("end_time", calendar));
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
