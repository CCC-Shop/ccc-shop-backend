package com.project.ccc_shop.common;

public interface UseCase<I, O> {
    void execute(I input, O output);
}
