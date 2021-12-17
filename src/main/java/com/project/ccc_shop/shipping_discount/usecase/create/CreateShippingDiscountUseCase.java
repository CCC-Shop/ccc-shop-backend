package com.project.ccc_shop.shipping_discount.usecase.create;

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
        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `shipping_discount` (`vender_id`, " +
                            "`policy_description`, " +
                            "`start_time`, " +
                            "`end_time`, " +
                            "`target_price`)" +
                            " VALUES (?,?,?,?,?)");

            stmt.setInt(1, input.getVenderId());
            stmt.setString(2, input.getPolicyDescription());
            stmt.setTimestamp(3, input.getStartTime());
            stmt.setTimestamp(4, input.getEndTime());
            stmt.setInt(5, input.getTargetPrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
