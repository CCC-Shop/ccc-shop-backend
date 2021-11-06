package com.project.ccc_shop.user.usecase;

import com.project.ccc_shop.common.Output;

public class CreateUserOutput implements Output {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
