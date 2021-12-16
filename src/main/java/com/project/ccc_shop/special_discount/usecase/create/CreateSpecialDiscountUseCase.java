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
                    "INSERT `special_discount` (`vender_id`, " +
                            "`policy_description`, " +
                            "`start_time`, " +
                            "`end_time`, " +
                            "`category`, " +
                            "`discount_rate`)" +
                            " VALUES (?,?,?,?,?,?)");

            stmt.setInt(1, input.getVenderId());
            stmt.setString(2, input.getPolicyDescription());
            stmt.setTimestamp(3, input.getStartTime());
            stmt.setTimestamp(4, input.getEndTime());
            stmt.setString(5, input.getCategory().toString());
            stmt.setDouble(6, input.getDiscountRate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
