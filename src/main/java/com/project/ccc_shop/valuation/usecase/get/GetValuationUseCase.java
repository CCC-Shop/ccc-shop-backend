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
                    "SELECT `valuation`.*, `user`.username FROM `valuation`, `user` " +
                            "WHERE `product_id` = ? " +
                            "AND `valuation`.`customer_id` = `user`.`id`");

            stmt.setInt(1, input.getProductId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String customerName = rs.getString("username");
                String comment = rs.getString("comment");
                int rating = rs.getInt("rating");
                Valuation valuation = new Valuation(customerName, comment, rating);
                valuationList.add(valuation);
            }

            output.setValuationList(valuationList);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
