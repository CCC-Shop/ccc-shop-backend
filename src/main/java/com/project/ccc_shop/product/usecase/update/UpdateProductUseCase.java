package com.project.ccc_shop.product.usecase.update;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            if (productExists(connection, input.getId())) {
                PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE `product` " +
                                "SET `name`=?, " +
                                "`vender_id`=?, " +
                                "`category`=?, " +
                                "`price`=?, " +
                                "`stock`=?, " +
                                "`warehouse_address`=?, " +
                                "`description`=?, " +
                                "`pictureURL`=?, " +
                                "`exist_flag`=? " +
                                "WHERE `id`=?");

                stmt.setString(1, input.getName());
                stmt.setInt(2, input.getVenderId());
                stmt.setString(3, input.getCategory().toString());
                stmt.setInt(4, input.getPrice());
                stmt.setInt(5, input.getStock());
                stmt.setString(6, input.getWarehouseAddress());
                stmt.setString(7, input.getDescription());
                stmt.setString(8, input.getPictureURL());
                stmt.setBoolean(9, input.getExistFlag());
                stmt.setInt(10, input.getId());

                stmt.executeUpdate();
            } else {
                throw new RuntimeException("product doesn't exist, where product id = " + input.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean productExists(Connection connection, int productId) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM `product` WHERE `id`= ?")) {
            stmt.setInt(1, productId);

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
