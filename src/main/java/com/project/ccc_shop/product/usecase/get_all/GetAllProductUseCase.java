package com.project.ccc_shop.product.usecase.get_all;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import com.project.ccc_shop.product.entity.Product;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllProductUseCase {

    private MySQLDriver mySQLDriver;

    public GetAllProductUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetAllProductOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            List<Product> productList = new ArrayList<>();

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT p.*, user.username, AVG(rating) " +
                            "FROM `product` AS p, `valuation` AS v, `user` " +
                            "WHERE p.id = v.product_id AND p.vender_id = user.id " +
                            "GROUP BY v.product_id " +
                            "ORDER BY `vender_id` ASC, `name` ASC, `stock` DESC, `price` ASC, `id` DESC");

            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int venderId = rs.getInt("vender_id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    int price = rs.getInt("price");
                    int stock = rs.getInt("stock");
                    String warehouseAddress = rs.getString("warehouse_address");
                    String description = rs.getString("description");
                    String pictureURL = rs.getString("pictureURL");
                    String venderName = rs.getString("username");
                    double rate = rs.getDouble("avg(rating)");

                    Product product = new Product(venderId, name, category, price, stock, warehouseAddress, description, pictureURL, venderName, rate);
                    productList.add(product);
                }
            }
            output.setProductList(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
