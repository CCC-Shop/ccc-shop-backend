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

            PreparedStatement stmt = connection.prepareStatement("SELECT `id` FROM `product` WHERE id = ?");
            stmt.setInt(1, input.getId());
            ResultSet rs = stmt.executeQuery();

            stmt = connection.prepareStatement("DELETE FROM `product` WHERE id = ?");
            stmt.setInt(1, input.getId());
            stmt.executeUpdate();

            if (!rs.next()) {
                output.setWorkCheck(false);
                throw new RuntimeException("delete product failed");
            } else {
                output.setWorkCheck(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
