package com.project.ccc_shop.user.usecase.login;

import com.project.ccc_shop.common.Input;

public class LoginUserInput implements Input {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
