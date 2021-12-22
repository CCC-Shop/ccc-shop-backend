package com.project.ccc_shop.product.usecase.get_all;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.product.entity.Category;
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
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<Product> productList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT p.id, p.vender_id, p.name, p.category, p.price, p.stock, p.warehouse_address, p.description, " +
                            "p.pictureURL, user.username, AVG(rating), p.exist_flag " +
                            "FROM `user`, `product` AS p LEFT OUTER JOIN `valuation` AS v " +
                            "ON p.id = v.product_id " +
                            "WHERE user.id = p.vender_id " +
                            "GROUP BY p.id");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setVenderId(rs.getInt("vender_id"));
                    product.setName(rs.getString("name"));
                    product.setCategory(Category.valueOf(rs.getString("category")));
                    product.setPrice(rs.getInt("price"));
                    product.setStock(rs.getInt("stock"));
                    product.setWarehouseAddress(rs.getString("warehouse_address"));
                    product.setDescription(rs.getString("description"));
                    product.setPictureURL(rs.getString("pictureURL"));
                    product.setVenderName(rs.getString("username"));
                    product.setRate(rs.getDouble("avg(rating)"));
                    product.setExistFlag(rs.getBoolean("exist_flag"));

                    productList.add(product);
                }
            }
            output.setProductList(productList);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
