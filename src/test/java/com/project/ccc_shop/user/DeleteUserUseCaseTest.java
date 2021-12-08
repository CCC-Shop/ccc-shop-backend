package com.project.ccc_shop.user;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.user.usecase.delete.DeleteUserInput;
import com.project.ccc_shop.user.usecase.delete.DeleteUserOutput;
import com.project.ccc_shop.user.usecase.delete.DeleteUserUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteUserUseCaseTest {
    @Test
    public void delete_user_Succeed() {

        MySQLDriver mySQLDriver = new MySQLDriver();

        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(mySQLDriver);
        DeleteUserInput input = new DeleteUserInput();
        DeleteUserOutput output = new DeleteUserOutput();

        input.setId(4);

        deleteUserUseCase.execute(input, output);

        assertTrue(output.getWorkCheck());
    }

    @Test
    public void delete_user_use_Failed() {

        MySQLDriver mySQLDriver = new MySQLDriver();

        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(mySQLDriver);
        DeleteUserInput input = new DeleteUserInput();
        DeleteUserOutput output = new DeleteUserOutput();

        input.setId(100);

        deleteUserUseCase.execute(input, output);

        assertFalse(output.getWorkCheck());
    }
}
