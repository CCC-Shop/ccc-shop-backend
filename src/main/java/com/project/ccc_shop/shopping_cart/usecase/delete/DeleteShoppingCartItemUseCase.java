package com.project.ccc_shop.shopping_cart.usecase.delete;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DeleteShoppingCartItemUseCase implements UseCase<DeleteShoppingCartItemInput, DeleteShoppingCartItemOutput> {

    private MySQLDriver mySQLDriver;

    public DeleteShoppingCartItemUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(DeleteShoppingCartItemInput input, DeleteShoppingCartItemOutput output) {

        try (Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement("SELECT `product_id`, `customer_id` FROM `shopping_cart` WHERE product_id = ? AND customer_id = ?;");
            stmt.setInt(1, input.getProductId());
            stmt.setInt(2, input.getCustomerId());
            ResultSet rs = stmt.executeQuery();

            stmt = connection.prepareStatement("DELETE FROM `shopping_cart` WHERE product_id = ? AND customer_id = ?");
            stmt.setInt(1, input.getProductId());
            stmt.setInt(2, input.getCustomerId());
            stmt.executeUpdate();

            if (!rs.next()) {
                output.setWorkCheck(false);
                throw new RuntimeException("delete shopping cart item failed");
            } else {
                output.setWorkCheck(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
