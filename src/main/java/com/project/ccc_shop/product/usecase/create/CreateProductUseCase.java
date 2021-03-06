package com.project.ccc_shop.product.usecase.create;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class CreateProductUseCase implements UseCase<CreateProductInput, CreateProductOutput> {

    private MySQLDriver mySQLDriver;

    public CreateProductUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateProductInput input, CreateProductOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `product` (`name`, " +
                            "`vender_id`, " +
                            "`category`, " +
                            "`price`, " +
                            "`stock`, " +
                            "`warehouse_address`, " +
                            "`description`, " +
                            "`pictureURL`, " +
                            "`exist_flag`)" +
                            " VALUES (?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, input.getName());
            stmt.setInt(2, input.getVenderId());
            stmt.setString(3, input.getCategory().toString());
            stmt.setInt(4, input.getPrice());
            stmt.setInt(5, input.getStock());
            stmt.setString(6, input.getWarehouseAddress());
            stmt.setString(7, input.getDescription());
            stmt.setString(8, input.getPictureURL());
            stmt.setBoolean(9, true);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
