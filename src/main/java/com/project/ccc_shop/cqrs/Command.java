package com.project.ccc_shop.cqrs;

import com.project.ccc_shop.usecase.Input;
import com.project.ccc_shop.usecase.Output;
import com.project.ccc_shop.usecase.UseCase;

// I 皆要是繼承 Input interface 才可接受, O 皆要是繼承 Output interface 才可接受
public interface Command<I extends Input, O extends Output> extends UseCase<I, O> {
}
