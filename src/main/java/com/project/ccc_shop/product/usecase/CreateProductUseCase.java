package com.project.ccc_shop.product.usecase;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class CreateProductUseCase implements UseCase<CreateProductInput, CreateProductOutput> {
    @Autowired
    private MySQLDriver mySQLDriver;
    private String id;

    public CreateProductUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateProductInput input, CreateProductOutput output) {
        id = UUID.randomUUID().toString();


        try(Connection connection = this.mySQLDriver.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `product` (" +
                            "  `id` int(6) NOT NULL," +
                            "  `name` varchar(100) NOT NULL," +
                            "  `quantity` int(11) NOT NULL," +
                            "  `category` varchar(100) NOT NULL," +
                            "  `size` varchar(5) NOT NULL," +
                            "  `price` float NOT NULL," +
                            "  `description` varchar(100) DEFAULT NULL," +
                            "  `pictureURL` varchar(500) DEFAULT NULL," +
                            "  PRIMARY KEY (`id`)," +
                            "  UNIQUE KEY `id_UNIQUE` (`id`)" +
                            ");"
            )) {
                stmt.setString(1, id);
                stmt.setString(2, input.getName());
                stmt.setInt(3, input.getQuantity());
                stmt.setString(4, input.getCategory().toString());
                stmt.setInt(5, input.getPrice());
                stmt.setString(6, input.getDescription());
                stmt.setString(7, input.getPictureURL());

                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `product` (`id`, `name`, `quantity`, `category`, `size`, `price`, `description`, `pictureURL` )" +
                            " VALUES (?,?,?,?,?,?,?)"
            )) {
                stmt.setString(1, id);
                stmt.setString(2, input.getName());
                stmt.setInt(3, input.getQuantity());
                stmt.setString(4, input.getCategory().toString());
                stmt.setInt(5, input.getPrice());
                stmt.setString(6, input.getDescription());
                stmt.setString(7, input.getPictureURL());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        output.setId(id);
        output.setName(input.getName());
    }
}
