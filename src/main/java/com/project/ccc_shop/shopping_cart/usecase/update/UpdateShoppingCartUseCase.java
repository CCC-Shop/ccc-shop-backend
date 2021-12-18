package com.project.ccc_shop.shopping_cart.usecase.update;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UpdateShoppingCartUseCase implements UseCase<UpdateShoppingCartInput, UpdateShoppingCartOutput> {

    private MySQLDriver mySQLDriver;

    public UpdateShoppingCartUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(UpdateShoppingCartInput input, UpdateShoppingCartOutput output) {

        try (Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `shopping_cart` SET `quantity` = ? WHERE `product_id`= ? AND `customer_id` = ?");

            stmt.setInt(1, input.getQuantity());
            stmt.setInt(2, input.getProductId());
            stmt.setInt(3, input.getCustomerId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
