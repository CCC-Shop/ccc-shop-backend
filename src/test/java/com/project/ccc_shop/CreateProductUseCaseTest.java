package com.project.ccc_shop;

import com.project.ccc_shop.usecase.Output;
import com.project.ccc_shop.usecase.product.CreateProductInput;
import com.project.ccc_shop.usecase.product.CreateProductUseCase;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateProductUseCaseTest {
    @Test     // annotation
    public void create_product_use_case() {
        MySQLDriver mySQLDriver = new MySQLDriver();

        CreateProductUseCase createProductBacklogUseCase = new CreateProductUseCase(mySQLDriver);
        CreateProductInput input = createProductBacklogUseCase.newInput();

        // CQRSCommandPresenter 才去 new 這個 output 物件, 因為最後系統還需要 presenter 去 build view model
        // output 只會用來 getId, 所以用 CQRSCommandPresenter 去 new
        Output output = ;
        // DTO(Data Transfer Object)
        // 在 Entity 用 UUID; DTO 用 String
        UUID projectId = UUID.randomUUID();          // UUID: Universally Unique Identifier 通用唯一辨識碼
        input.setProjectId(projectId.toString());    // 知道自己歸屬於哪個 project

        createProductBacklogUseCase.execute(input, output);

        assertNotNull(output.getId());  // product backlog's Id
        // CQRSCommandPresenter 這裡不能 getProjectId 因為要減少output的資料
        ProductBacklog productBacklog = productBacklogRepository.findById(output.getId());
        assertEquals(output.getId(), productBacklog.getId().toString());
        assertEquals(1, eventHandler.getProductBacklogCreatedEventCount());
    }

}
