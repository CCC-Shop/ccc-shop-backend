package com.project.ccc_shop.user.usecase.get_all;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.user.entity.Identity;
import com.project.ccc_shop.user.entity.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllUserUseCase {

    private MySQLDriver mySQLDriver;

    public GetAllUserUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    public void execute(GetAllUserOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `user`;");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setIdentity(Identity.valueOf(rs.getString("identity")));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setCreditCard(rs.getString("credit_card"));
                    user.setAddress(rs.getString("address"));

                    users.add(user);
                }
            }

            output.setUserList(users);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
