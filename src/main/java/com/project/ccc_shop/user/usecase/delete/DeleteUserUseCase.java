package com.project.ccc_shop.user.usecase.delete;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.common.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DeleteUserUseCase implements UseCase<DeleteUserInput, DeleteUserOutput>{
    @Autowired
    private MySQLDriver mySQLDriver;

    public DeleteUserUseCase(MySQLDriver mySQLDriver) {
        this.mySQLDriver = mySQLDriver;
    }

    @Override
    public void execute(DeleteUserInput input, DeleteUserOutput output) {

        try (Connection connection = this.mySQLDriver.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement("SELECT `id` FROM `user` WHERE id = ?");
            stmt.setInt(1, input.getId());
            ResultSet rs = stmt.executeQuery();

            stmt = connection.prepareStatement("DELETE FROM `user` WHERE id = ?");
            stmt.setInt(1, input.getId());
            stmt.executeUpdate();

            if (!rs.next()){
                output.setWorkCheck(false);
            } else {
                output.setWorkCheck(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



