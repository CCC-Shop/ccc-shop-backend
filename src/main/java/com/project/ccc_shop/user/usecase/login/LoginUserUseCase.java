package com.project.ccc_shop.user.usecase.login;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginUserUseCase implements UseCase<LoginUserInput, LoginUserOutput> {
    @Autowired
    private MySQLDriver mySQLDriver;

    public LoginUserUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(LoginUserInput input, LoginUserOutput output) {

        try (Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `user` WHERE `username` = ? and `password` = ?");

            stmt.setString(1, input.getUsername());
            stmt.setString(2, input.getPassword());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                output.setId(rs.getInt("id"));
                output.setUsername(rs.getString("username"));
                output.setIdentity(rs.getString("identity"));
                output.setPhone(rs.getString("phone"));
                output.setEmail(rs.getString("email"));
                output.setCreditCard(rs.getString("credit_card"));
                output.setAddress(rs.getString("address"));
            } else {
                output.setUsername("fail");
                throw new LoginFailedException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw e;
        }
    }
}
