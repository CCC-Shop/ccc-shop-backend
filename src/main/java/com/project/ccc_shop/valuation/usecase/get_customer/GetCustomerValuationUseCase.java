package com.project.ccc_shop.valuation.usecase.get_customer;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.valuation.entity.Valuation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetCustomerValuationUseCase implements UseCase<GetCustomerValuationInput, GetCustomerValuationOutput> {

    private MySQLDriver mySQLDriver;

    public GetCustomerValuationUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetCustomerValuationInput input, GetCustomerValuationOutput output) {
        List<Valuation> valuationList = new ArrayList<>();

        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `valuation` " +
                            "WHERE `customer_id`=? " +
                            "AND `product_id`=?");

            stmt.setInt(1, input.getCustomerId());
            stmt.setInt(2, input.getProductId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                output.setComment(rs.getString("comment"));
                output.setRating(rs.getInt("rating"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
