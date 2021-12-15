package com.project.ccc_shop.seasonings_discount.usecase.get;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class GetSeasoningsDiscountUseCase implements UseCase<GetSeasoningsDiscountInput, GetSeasoningsDiscountOutput> {

    private MySQLDriver mySQLDriver;

    public GetSeasoningsDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetSeasoningsDiscountInput input, GetSeasoningsDiscountOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `seasonings_discount` WHERE `discount_code`=?");

            stmt.setInt(1, input.getDiscountCode());

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();

            output.setVenderId(resultSet.getInt("vender_id"));
            output.setPolicyDescription(resultSet.getString("policy_description"));
            output.setStartTime(resultSet.getString("start_time"));
            output.setEndTime(resultSet.getString("end_time"));
            output.setDiscountRate(resultSet.getDouble("discount_rate"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
