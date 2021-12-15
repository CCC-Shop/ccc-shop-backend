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
                    "SELECT p.id, p.vender_id, p.name, p.category, p.price, p.stock, p.warehouse_address, p.description, p.pictureURL, user.username, AVG(rating)" +
                            "            FROM `user`, `product` AS p LEFT OUTER JOIN `valuation` AS v" +
                            "            ON p.id = v.product_id" +
                            "            WHERE user.id = p.vender_id" +
                            "            GROUP BY p.id;");


            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("id");
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

                    Product product = new Product(id, venderId, name, category, price, stock, warehouseAddress, description, pictureURL, venderName, rate);
                    productList.add(product);
                }
            }
            output.setProductList(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
