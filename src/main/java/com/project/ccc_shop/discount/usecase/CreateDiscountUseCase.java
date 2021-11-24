package com.project.ccc_shop.discount.usecase;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class CreateDiscountUseCase implements UseCase<CreateDiscountInput, CreateDiscountOutput> {

    private MySQLDriver mySQLDriver;

    public CreateDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateDiscountInput input, CreateDiscountOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `discount` (`discount_code`, `policy_description`, `start_time`, `end_time`, `discount_rate`, `target_price`, `category` )" +
                            " VALUES (?,?,?,?,?,?,?)");

                stmt.setString(1, input.getDiscountCode());
                stmt.setString(2, input.getPolicyDescription());
                stmt.setTimestamp(3, input.getStartTime());
                stmt.setTimestamp(4, input.getEndTime());
                stmt.setDouble(5, input.getDiscountRate());
                stmt.setInt(6, input.getTargetPrice());
                stmt.setString(7, input.getCategory().toString());

                stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        output.setDiscountCode(input.getDiscountCode());
    }
}
