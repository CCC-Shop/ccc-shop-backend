package com.project.ccc_shop.user.usecase.update;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UpdateUserUseCase implements UseCase<UpdateUserInput, UpdateUserOutput> {

    private MySQLDriver mySQLDriver;

    public UpdateUserUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(UpdateUserInput input, UpdateUserOutput output) {
        try (Connection connection = this.mySQLDriver.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `user` SET " +
                            "`username`=?, " +
                            "`identity`=?, " +
                            "`phone`=?, " +
                            "`email`=?, " +
                            "`credit_card`=?, " +
                            "`address`=? " +
                            "WHERE `id`=?;");

            stmt.setString(1, input.getUsername());
            stmt.setString(2, input.getIdentity().toString());
            stmt.setString(3, input.getPhone());
            stmt.setString(4, input.getEmail());
            stmt.setString(5, input.getCreditCard());
            stmt.setString(6, input.getAddress());
            stmt.setInt(7, input.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
