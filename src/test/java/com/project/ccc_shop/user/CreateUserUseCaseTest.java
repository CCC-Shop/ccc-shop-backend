package com.project.ccc_shop.user;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.user.entity.Identity;
import com.project.ccc_shop.user.usecase.create.CreateUserInput;
import com.project.ccc_shop.user.usecase.create.CreateUserOutput;
import com.project.ccc_shop.user.usecase.create.CreateUserUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateUserUseCaseTest {
    @Test
    public void create_user_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(mySQLDriver);
        CreateUserInput input = new CreateUserInput();
        CreateUserOutput output = new CreateUserOutput();

        input.setUsername("test user");
        input.setIdentity(Identity.ADMIN);
        input.setPassword("admin");
        input.setPhone("091234567");
        input.setEmail("admin@gmail.com");
        input.setCreditCard("0000-1111-2222-3333");
        input.setAddress("台北市大安區忠孝東路xxx號");

        createUserUseCase.execute(input, output);

        assertNotNull(output.getUsername());
        assertEquals("test user", output.getUsername());
    }
}
