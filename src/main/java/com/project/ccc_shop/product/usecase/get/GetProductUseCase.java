package com.project.ccc_shop.product.usecase.get;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
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
        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `product` WHERE `id`=?");
            stmt.setInt(1, input.getId());

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    output.setName(rs.getString("name"));
                    output.setVenderId(rs.getInt("vender_id"));
                    output.setCategory(rs.getString("category"));
                    output.setPrice(rs.getInt("price"));
                    output.setStock(rs.getInt("stock"));
                    output.setWarehouseAddress(rs.getString("warehouse_address"));
                    output.setDescription(rs.getString("description"));
                    output.setPictureURL(rs.getString("pictureURL"));
                    output.setExistFlag(rs.getBoolean("exist_flag"));
                }
                else {
                    throw new RuntimeException("product doesn't exist, where product id = " + input.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
