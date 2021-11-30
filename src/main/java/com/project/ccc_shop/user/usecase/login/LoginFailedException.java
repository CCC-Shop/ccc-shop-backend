package com.project.ccc_shop.user.usecase.login;

public class LoginFailedException extends Exception{
    public LoginFailedException() {
        super("Login Failed!");
    }
}
