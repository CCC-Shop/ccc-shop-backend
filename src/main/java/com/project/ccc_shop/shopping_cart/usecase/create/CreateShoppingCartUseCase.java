package com.project.ccc_shop.shopping_cart.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class CreateShoppingCartUseCase implements UseCase<CreateShoppingCartInput, CreateShoppingCartOutput> {

    private MySQLDriver mySQLDriver;

    public CreateShoppingCartUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateShoppingCartInput input, CreateShoppingCartOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `shopping_cart` (`product_id`, `customer_id`, `quantity`) " +
                            "VALUES (?,?,?)");

            stmt.setInt(1, input.getProductId());
            stmt.setInt(2, input.getCustomerId());
            stmt.setInt(3, input.getQuantity());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
