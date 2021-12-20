package com.project.ccc_shop.valuation.usecase.get;

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
public class GetValuationUseCase implements UseCase<GetValuationInput, GetValuationOutput> {

    private MySQLDriver mySQLDriver;

    public GetValuationUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetValuationInput input, GetValuationOutput output) {

        List<Valuation> valuationList = new ArrayList<>();

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `valuation` WHERE `product_id` = ?");

            stmt.setInt(1, input.getProductId());
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                throw new RuntimeException("The product doesn't have any valuation!");
            }

            while(rs.next()){
                int customer_id = rs.getInt("customer_id");
                String comment = rs.getString("comment");
                int rating = rs.getInt("rating");
                Valuation valuation = new Valuation(customer_id, comment, rating);
                valuationList.add(valuation);
            }

            output.setValuationList(valuationList);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
