package com.project.ccc_shop.product.usecase.delete;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DeleteProductUseCase implements UseCase<DeleteProductInput, DeleteProductOutput> {

    private MySQLDriver mySQLDriver;

    public DeleteProductUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(DeleteProductInput input, DeleteProductOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            if (productExists(connection, input.getId())) {
                PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE `product` SET `exist_flag` = ? WHERE id = ?");
                stmt.setBoolean(1, false);
                stmt.setInt(2, input.getId());

                stmt.executeUpdate();
            }
            else {
                throw new RuntimeException("product not exist, where product id = " + input.getId());
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
