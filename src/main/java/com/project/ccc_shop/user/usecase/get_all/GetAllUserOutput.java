package com.project.ccc_shop.user.usecase.get_all;

import com.project.ccc_shop.common.Output;
import com.project.ccc_shop.user.entity.User;

import java.util.List;

public class GetAllUserOutput extends Output {

    private List<User> users;

    public void setUserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUserList() {
        return this.users;
    }
}
