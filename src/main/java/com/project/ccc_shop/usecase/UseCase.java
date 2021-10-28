package com.project.ccc_shop.usecase;

public interface UseCase<I, O> {
    void execute(I input, O output);
    I newInput();
}
