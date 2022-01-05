package com.project.ccc_shop.valuation.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CreateValuationUseCase implements UseCase<CreateValuationInput, CreateValuationOutput> {

    private MySQLDriver mySQLDriver;

    public CreateValuationUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateValuationInput input, CreateValuationOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            if (!valuationExists(connection, input.getCustomerId(), input.getProductId())) {
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT `valuation` (`customer_id`, `product_id`, `comment`, `rating`)" +
                                " VALUES (?,?,?,?)");

                stmt.setInt(1, input.getCustomerId());
                stmt.setInt(2, input.getProductId());
                stmt.setString(3, input.getComment());
                stmt.setInt(4, input.getRating());

                stmt.executeUpdate();

                output.setCustomerId(input.getCustomerId());
            } else {
                PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE `valuation` " +
                                "SET `comment`=?, `rating`=? " +
                                "WHERE `customer_id`=? " +
                                "AND `product_id`=?");

                stmt.setString(1, input.getComment());
                stmt.setInt(2, input.getRating());
                stmt.setInt(3, input.getCustomerId());
                stmt.setInt(4, input.getProductId());

                stmt.executeUpdate();

                output.setCustomerId(input.getCustomerId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean valuationExists(Connection connection, int customerId, int productId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM `valuation` WHERE `customer_id`=? AND `product_id`=?")) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }
}