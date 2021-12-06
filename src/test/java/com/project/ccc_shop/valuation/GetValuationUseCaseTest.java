package com.project.ccc_shop.valuation;

import com.project.ccc_shop.common.MySQLDriver;
import com.project.ccc_shop.valuation.usecase.get.GetValuationInput;
import com.project.ccc_shop.valuation.usecase.get.GetValuationOutput;
import com.project.ccc_shop.valuation.usecase.get.GetValuationUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetValuationUseCaseTest {
    @Test
    public void get_valuation_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        GetValuationUseCase getValuationUseCase = new GetValuationUseCase(mySQLDriver);
        GetValuationInput input = new GetValuationInput();
        GetValuationOutput output = new GetValuationOutput();

        input.setCustomerId(3);
        input.setProductId(2);

        getValuationUseCase.execute(input, output);

        assertNotNull(output.getCustomerId());
        assertEquals(3, output.getCustomerId());
        assertNotNull(output.getProductId());
        assertEquals(2, output.getProductId());
        assertNotNull(output.getComment());
        assertEquals("Excellent!!!", output.getComment());
        assertNotNull(output.getRating());
        assertEquals(5, output.getRating());
    }
}
