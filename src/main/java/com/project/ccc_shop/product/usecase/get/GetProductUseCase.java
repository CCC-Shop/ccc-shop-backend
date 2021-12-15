package com.project.ccc_shop.product.usecase.get;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.product.entity.Category;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

@Service
public class GetProductUseCase implements UseCase<GetProductInput, GetProductOutput> {

    private MySQLDriver mySQLDriver;

    public GetProductUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(GetProductInput input, GetProductOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `product` WHERE `id`=?");

            stmt.setInt(1, input.getId());

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();

            output.setName(resultSet.getString("name"));
            output.setUserId(resultSet.getInt("vender_id"));
            output.setCategory(resultSet.getString("category"));
            output.setPrice(resultSet.getInt("price"));
            output.setStock(resultSet.getInt("stock"));
            output.setWarehouseAddress(resultSet.getString("warehouse_address"));
            output.setDescription(resultSet.getString("description"));
            output.setPictureURL(resultSet.getString("pictureURL"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
