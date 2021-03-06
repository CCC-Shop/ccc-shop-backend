package com.project.ccc_shop.special_discount.usecase.get_vender;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.product.entity.Category;
import com.project.ccc_shop.seasonings_discount.usecase.get_current.GetCurrentSeasoningsDiscountUseCase;
import com.project.ccc_shop.special_discount.entity.SpecialDiscount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class GetVenderSpecialDiscountUseCase implements UseCase<GetVenderSpecialDiscountInput, GetVenderSpecialDiscountOutput> {
    private MySQLDriver mySQLDriver;

    public GetVenderSpecialDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetVenderSpecialDiscountInput input, GetVenderSpecialDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<SpecialDiscount> discounts = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * " +
                            "FROM `special_discount` " +
                            "WHERE `vender_id` = ?;");

            stmt.setInt(1, input.getVenderId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SpecialDiscount specialDiscount = new SpecialDiscount();
                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));  // set timezone
                    specialDiscount.setDiscountCode(rs.getInt("discount_code"));
                    specialDiscount.setVenderId(rs.getInt("vender_id"));
                    specialDiscount.setVenderName(GetCurrentSeasoningsDiscountUseCase.queryVenderName(connection, rs.getInt("vender_id")));
                    specialDiscount.setPolicyDescription(rs.getString("policy_description"));
                    specialDiscount.setStartTime(rs.getTimestamp("start_time", calendar));
                    specialDiscount.setEndTime(rs.getTimestamp("end_time", calendar));
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
}
