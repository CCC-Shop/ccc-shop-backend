package com.project.ccc_shop.special_discount.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class CreateSpecialDiscountUseCase implements UseCase<CreateSpecialDiscountInput, CreateSpecialDiscountOutput> {

    private MySQLDriver mySQLDriver;

    public CreateSpecialDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateSpecialDiscountInput input, CreateSpecialDiscountOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `special_discount` (`product_id`, `vender_id`, `policy_description`, `start_time`, `end_time`, `category`)" +
                            " VALUES (?,?,?,?,?,?)");

//            stmt.setString(1, input.getDiscountCode());
            stmt.setInt(1, input.getProductId());
            stmt.setInt(2, input.getVenderId());
            stmt.setString(3, input.getPolicyDescription());
            stmt.setTimestamp(4, input.getStartTime());
            stmt.setTimestamp(5, input.getEndTime());
            stmt.setString(6, input.getCategory());

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
                "SELECT `discount_code` FROM `special_discount` WHERE `policy_description`= ?")) {
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
