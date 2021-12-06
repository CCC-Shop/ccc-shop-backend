package com.project.ccc_shop.valuation.usecase.get;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class GetValuationUseCase implements UseCase<GetValuationInput, GetValuationOutput> {

    private MySQLDriver mySQLDriver;

    public GetValuationUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetValuationInput input, GetValuationOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `valuation` WHERE `customer_id`=? AND `product_id`=?");

            stmt.setInt(1, input.getCustomerId());
            stmt.setInt(2, input.getProductId());

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();

            output.setCustomerId(resultSet.getInt("customer_id"));
            output.setProductId(resultSet.getInt("product_id"));
            output.setComment(resultSet.getString("comment"));
            output.setRating(resultSet.getInt("rating"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
