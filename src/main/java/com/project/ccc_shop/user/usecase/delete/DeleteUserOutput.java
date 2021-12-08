package com.project.ccc_shop.user.usecase.delete;

import com.project.ccc_shop.common.Output;

public class DeleteUserOutput implements Output{
    private boolean workCheck;

    public void setWorkCheck(boolean workCheck) {
        this.workCheck = workCheck;
    }

    public boolean getWorkCheck() {
        return workCheck;
    }
}
