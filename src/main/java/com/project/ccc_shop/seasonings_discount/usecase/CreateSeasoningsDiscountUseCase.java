package com.project.ccc_shop.seasonings_discount.usecase;

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
                    "INSERT `seasonings_discount` (`order_id`, `vendor_id`, `policy_description`, `start_time`, `end_time`, `discount_rate`)" +
                            " VALUES (?,?,?,?,?,?)");
//                    "INSERT `discount` (`discount_code`, `policy_description`, `start_time`, `end_time`, `discount_rate`, `target_price`, `category` )" +
//                            " VALUES (?,?,?,?,?,?,?)");

//            stmt.setString(1, input.getDiscountCode());
            stmt.setInt(1, input.getOrderId());
            stmt.setInt(2, input.getVenderId());
            stmt.setString(3, input.getPolicyDescription());
            stmt.setTimestamp(4, input.getStartTime());
            stmt.setTimestamp(5, input.getEndTime());
            stmt.setDouble(6, input.getDiscountRate());
//                stmt.setInt(6, input.getTargetPrice());
//                stmt.setString(7, input.getCategory().toString());

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
