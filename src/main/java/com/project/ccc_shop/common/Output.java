package com.project.ccc_shop.common;

public abstract class Output {
    String message = "Success!";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
