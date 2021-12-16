package com.project.ccc_shop.user;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.user.entity.Identity;
import com.project.ccc_shop.user.usecase.update.UpdateUserInput;
import com.project.ccc_shop.user.usecase.update.UpdateUserOutput;
import com.project.ccc_shop.user.usecase.update.UpdateUserUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUserUseCaseTest {
    @Test
    public void update_user() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(mySQLDriver);
        UpdateUserInput input = new UpdateUserInput();
        UpdateUserOutput output = new UpdateUserOutput();
        input.setId(9);
        input.setUsername("Eddie");
        input.setIdentity(Identity.valueOf("STAFF"));
        input.setPassword("Eddie123");
        input.setPhone("0987654321");
        input.setEmail("eddie@gmail.com");
        input.setCreditCard("1234567-1234567");
        input.setAddress("台北市大安區忠孝東路xxx號");

        updateUserUseCase.execute(input, output);

        assertEquals("Success!", output.getMessage());
    }
}
