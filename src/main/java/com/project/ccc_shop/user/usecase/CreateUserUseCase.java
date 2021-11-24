package com.project.ccc_shop.user.usecase;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class CreateUserUseCase implements UseCase<CreateUserInput, CreateUserOutput> {
    @Autowired
    private MySQLDriver mySQLDriver;

    public CreateUserUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(CreateUserInput input, CreateUserOutput output) {

        try(Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT `user` (`username`, `identity`, `password`, `phone`, `email`, `credit_card`, `address` )" +
                            " VALUES (?,?,?,?,?,?,?)");

            stmt.setString(1, input.getUsername());
            stmt.setString(2, input.getIdentity().toString());
            stmt.setString(3, input.getPassword());
            stmt.setString(4, input.getPhone());
            stmt.setString(5, input.getEmail());
            stmt.setString(6, input.getCreditCard());
            stmt.setString(7, input.getAddress());

            stmt.executeUpdate();

            output.setUsername(input.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
