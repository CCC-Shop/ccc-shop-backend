package com.project.ccc_shop.usecase.product;

import com.project.ccc_shop.cqrs.Command;
import com.project.ccc_shop.usecase.Output;
import com.project.ccc_shop.usecase.product.CreateProductInput;

public class CreateProductUseCase implements Command<CreateProductInput, Output> {

    @Override
    public void execute(CreateProductInput input, Output output) {

    }

    @Override
    public CreateProductInput newInput() {
        return null;
    }
}
