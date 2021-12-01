package com.project.ccc_shop.product.usecase.update;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UpdateProductUseCase implements UseCase<UpdateProductInput, UpdateProductOutput> {

    private MySQLDriver mySQLDriver;

    public UpdateProductUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(UpdateProductInput input, UpdateProductOutput output) {

        try (Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `product` SET `name`=?, `vender_id`=?, `category`=?, `price`=?, `stock`=?, `warehouse_address`=?, `description`=?, `pictureURL`=?" +                             " WHERE `id`=?");

            stmt.setString(1, input.getName());
            stmt.setInt(2, input.getUserId());
            stmt.setString(3, input.getCategory().toString());
            stmt.setInt(4, input.getPrice());
            stmt.setInt(5, input.getStock());
            stmt.setString(6, input.getWarehouseAddress());
            stmt.setString(7, input.getDescription());
            stmt.setString(8, input.getPictureURL());
            stmt.setInt(9, input.getId());

            stmt.executeUpdate();

            output.setName(input.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
