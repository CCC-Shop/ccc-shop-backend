package com.project.ccc_shop.valuation;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationInput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationOutput;
import com.project.ccc_shop.valuation.usecase.create.CreateValuationUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateValuationUseCaseTest {
    @Test
    public void create_valuation_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateValuationUseCase createValuationUseCase = new CreateValuationUseCase(mySQLDriver);
        CreateValuationInput input = new CreateValuationInput();
        CreateValuationOutput output = new CreateValuationOutput();

        input.setCustomerId(3);
        input.setProductId(2);
        input.setComment("Excellent!!!");
        input.setRating(5);

        createValuationUseCase.execute(input, output);

        assertNotNull(output.getCustomerId());
        assertEquals(3, output.getCustomerId());
    }
}
