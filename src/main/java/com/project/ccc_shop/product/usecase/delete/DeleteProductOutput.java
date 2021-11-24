package com.project.ccc_shop.product.usecase.delete;

import com.project.ccc_shop.common.Output;

public class DeleteProductOutput implements Output {
    private boolean workCheck = true;

    public boolean check() {
        return workCheck;
    }
}
