package com.project.ccc_shop.seasonings_discount.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class CreateSeasoningsDiscountUseCase implements UseCase<CreateSeasoningsDiscountInput, CreateSeasoningsDiscountOutput> {

    private MySQLDriver mySQLDriver;

    public CreateSeasoningsDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateSeasoningsDiscountInput input, CreateSeasoningsDiscountOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `seasonings_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `discount_rate`)" +
                            " VALUES (?,?,?,?,?)");

//            stmt.setString(1, input.getDiscountCode());
            stmt.setInt(1, input.getVenderId());
            stmt.setString(2, input.getPolicyDescription());
            stmt.setTimestamp(3, input.getStartTime());
            stmt.setTimestamp(4, input.getEndTime());
            stmt.setDouble(5, input.getDiscountRate());

            stmt.executeUpdate();

//            output.setDiscountCode(input.getDiscountCode());
            int discount_code = getDiscountCode(connection, input.getPolicyDescription(), input.getStartTime());
            output.setDiscountCode(discount_code);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getDiscountCode(Connection connection, String policyDescription, Timestamp startTime){
        try (PreparedStatement stmt = connection.prepareStatement(
//                && `start_time` = ?"
                "SELECT `discount_code` FROM `seasonings_discount` WHERE `policy_description`= ?")) {
            stmt.setString(1, policyDescription);
//            stmt.setTimestamp(2, startTime);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    return rs.getInt("discount_code");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Discount code not found, where policy_description=" + policyDescription + ", start_time=" + startTime + ".");
    }
}
