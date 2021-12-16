package com.project.ccc_shop.user;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.user.entity.Identity;
import com.project.ccc_shop.user.entity.User;
import com.project.ccc_shop.user.usecase.get_all.GetAllUserOutput;
import com.project.ccc_shop.user.usecase.get_all.GetAllUserUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllUserUseCaseTest {
    @Test
    public void get_all_user_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();
        GetAllUserUseCase getAllUserUseCase = new GetAllUserUseCase(mySQLDriver);
        GetAllUserOutput output = new GetAllUserOutput();

        getAllUserUseCase.execute(output);
        List<User> testUserList = output.getUserList();

        assertEquals(10, testUserList.size());
        User user = testUserList.get(0);
        assertEquals(1, user.getId());
        assertEquals("Admin", user.getUsername());
        assertEquals(Identity.ADMIN, user.getIdentity());
        assertEquals("Admin123", user.getPassword());
        assertEquals("0912341234", user.getPhone());
        assertEquals("admin@gmail.com", user.getEmail());
        assertEquals("0000111-2222333", user.getCreditCard());
        assertEquals("home", user.getAddress());

        user = testUserList.get(2);
        assertEquals(3, user.getId());
        assertEquals("ASUS", user.getUsername());
        assertEquals(Identity.STAFF, user.getIdentity());
        assertEquals("ASUS123", user.getPassword());
        assertEquals("0900112233", user.getPhone());
        assertEquals("google@gmail.com", user.getEmail());
        assertEquals("台北市大安區忠孝東路xxx號5F", user.getAddress());

        user = testUserList.get(5);
        assertEquals(6, user.getId());
        assertEquals("Mandy", user.getUsername());
        assertEquals(Identity.CUSTOMER, user.getIdentity());
        assertEquals("Mandy123", user.getPassword());
        assertEquals("0987654321", user.getPhone());
        assertEquals("mandy@gmail.com", user.getEmail());
        assertEquals("台北市大安區忠孝東路xxx號5F", user.getAddress());

        user = testUserList.get(9);
        assertEquals(10, user.getId());
        assertEquals("Teddy", user.getUsername());
        assertEquals(Identity.CUSTOMER, user.getIdentity());
        assertEquals("Teddy123", user.getPassword());
        assertEquals("0987654321", user.getPhone());
        assertEquals("teddy@gmail.com", user.getEmail());
        assertEquals("8822333-4444555", user.getCreditCard());
        assertEquals("台北市大安區忠孝東路xxx號5F", user.getAddress());
    }
}
