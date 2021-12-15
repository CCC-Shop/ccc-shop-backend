package com.project.ccc_shop.shipping_discount.usecase;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class CreateShippingDiscountUseCase implements UseCase<CreateShippingDiscountInput, CreateShippingDiscountOutput> {

    private MySQLDriver mySQLDriver;

    public CreateShippingDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateShippingDiscountInput input, CreateShippingDiscountOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `shipping_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `target_price`)" +
                            " VALUES (?,?,?,?,?)");

//            stmt.setString(1, input.getDiscountCode());
            stmt.setInt(1, input.getVenderId());
            stmt.setString(2, input.getPolicyDescription());
            stmt.setTimestamp(3, input.getStartTime());
            stmt.setTimestamp(4, input.getEndTime());
            stmt.setInt(5, input.getTargetPrice());

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
                "SELECT `discount_code` FROM `shipping_discount` WHERE `policy_description`= ?")) {
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
