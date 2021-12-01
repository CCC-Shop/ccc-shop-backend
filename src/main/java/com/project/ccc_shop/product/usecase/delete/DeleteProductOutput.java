package com.project.ccc_shop.product.usecase.delete;

import com.project.ccc_shop.common.Output;

public class DeleteProductOutput implements Output {

    private boolean workCheck;

    public void setWorkCheck(boolean workCheck) {
        this.workCheck = workCheck;
    }

    public boolean getWorkCheck() {
        return workCheck;
    }

}
