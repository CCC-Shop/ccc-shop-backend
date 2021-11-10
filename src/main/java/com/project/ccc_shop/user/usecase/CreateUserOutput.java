package com.project.ccc_shop.user.usecase;

import com.project.ccc_shop.common.Output;

public class CreateUserOutput implements Output {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
