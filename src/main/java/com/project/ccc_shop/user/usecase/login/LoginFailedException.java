package com.project.ccc_shop.user.usecase.login;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login Failed!");
    }
}
