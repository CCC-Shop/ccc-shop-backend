package com.project.ccc_shop.seasonings_discount.usecase.edit;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.seasonings_discount.entity.SeasoningsDiscount;
import com.project.ccc_shop.seasonings_discount.usecase.edit.EditSeasoningsDiscountInput;
import com.project.ccc_shop.seasonings_discount.usecase.edit.EditSeasoningsDiscountOutput;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class EditSeasoningsDiscountUseCase implements UseCase<EditSeasoningsDiscountInput, EditSeasoningsDiscountOutput> {

    private MySQLDriver mySQLDriver;

    public EditSeasoningsDiscountUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(EditSeasoningsDiscountInput input, EditSeasoningsDiscountOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `seasonings_discount` SET `vender_id`= ? WHERE `discount_code`= ?");

            stmt.setInt(1, input.getVenderId());
            stmt.setInt(2, input.getDiscountCode());

            stmt.executeUpdate();

            updatePolicyDescription(connection, input.getPolicyDescription(), input.getDiscountCode());
            updateStartTime(connection, input.getStartTime(), input.getDiscountCode());
            updateEndTime(connection, input.getEndTime(), input.getDiscountCode());
            updateDiscountRate(connection, input.getDiscountRate(), input.getDiscountCode());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void updatePolicyDescription(Connection connection, String policyDescription, int discountCode) {

        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE `seasonings_discount` SET `policy_description`= ? WHERE `discount_code`= ?")) {
            stmt.setString(1, policyDescription);
            stmt.setInt(2, discountCode);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void updateStartTime(Connection connection, Timestamp startTime, int discountCode) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE `seasonings_discount` SET `start_time`= ? WHERE `discount_code`= ?")) {
            stmt.setTimestamp(1, startTime);
            stmt.setInt(2, discountCode);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void updateEndTime(Connection connection, Timestamp endTime, int discountCode) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE `seasonings_discount` SET `end_time`= ? WHERE `discount_code`= ?")) {
            stmt.setTimestamp(1, endTime);
            stmt.setInt(2, discountCode);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void updateDiscountRate(Connection connection, double discountRate, int discountCode) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE `seasonings_discount` SET `discount_rate`= ? WHERE `discount_code`= ?")) {
            stmt.setDouble(1, discountRate);
            stmt.setInt(2, discountCode);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}