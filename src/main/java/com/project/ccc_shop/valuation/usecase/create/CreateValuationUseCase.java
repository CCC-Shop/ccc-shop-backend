package com.project.ccc_shop.valuation.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class CreateValuationUseCase implements UseCase<CreateValuationInput, CreateValuationOutput> {

    private MySQLDriver mySQLDriver;

    public CreateValuationUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateValuationInput input, CreateValuationOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `valuation` (`customer_id`, `product_id`, `comment`, `rating`)" +
                            " VALUES (?,?,?,?)");

            stmt.setInt(1, input.getCustomerId());
            stmt.setInt(2, input.getProductId());
            stmt.setString(3, input.getComment().toString());
            stmt.setInt(4, input.getRating());

            stmt.executeUpdate();

            output.setCustomerId(input.getCustomerId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}