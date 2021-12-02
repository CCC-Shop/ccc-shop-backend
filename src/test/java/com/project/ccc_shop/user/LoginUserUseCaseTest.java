package com.project.ccc_shop.user;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.user.usecase.login.LoginUserInput;
import com.project.ccc_shop.user.usecase.login.LoginUserOutput;
import com.project.ccc_shop.user.usecase.login.LoginUserUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginUserUseCaseTest {
    @Test
    public void login() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        LoginUserUseCase loginUserUseCase = new LoginUserUseCase(mySQLDriver);
        LoginUserInput input = new LoginUserInput();
        LoginUserOutput output = new LoginUserOutput();

        input.setUsername("test user");
        input.setPassword("admin");

        loginUserUseCase.execute(input, output);

        assertNotNull(output.getIdentity());
        assertEquals("test user", output.getUsername());
        assertEquals("091234567", output.getPhone());
        assertEquals("admin@gmail.com", output.getEmail());
        assertEquals("台北市大安區忠孝東路xxx號", output.getAddress());
    }

    @Test
    public void login_failed_due_to_no_matched_user() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        LoginUserUseCase loginUserUseCase = new LoginUserUseCase(mySQLDriver);
        LoginUserInput input = new LoginUserInput();
        LoginUserOutput output = new LoginUserOutput();

        input.setUsername("none");
        input.setPassword("admin");

        loginUserUseCase.execute(input, output);

        assertEquals("fail", output.getUsername());
        assertNull(output.getIdentity());
        assertNull(output.getPhone());
        assertNull(output.getEmail());
        assertNull(output.getAddress());
    }
}
