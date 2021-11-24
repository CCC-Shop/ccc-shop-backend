package com.project.ccc_shop.user.usecase.adapter;

import com.project.ccc_shop.user.usecase.CreateUserInput;
import com.project.ccc_shop.user.usecase.CreateUserOutput;
import com.project.ccc_shop.user.usecase.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    CreateUserUseCase createUserUseCase;

    @Autowired
    public void setCreateUserUseCase(CreateUserUseCase createUserUseCase){
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping(value = "/add/user")
    public ResponseEntity<CreateUserOutput> addUser(@RequestBody CreateUserInput requestBody) {
        CreateUserInput input = new CreateUserInput();
        CreateUserOutput output = new CreateUserOutput();

        input.setUsername(requestBody.getUsername());
        input.setIdentity(requestBody.getIdentity());
        input.setPassword(requestBody.getPassword());
        input.setPhone(requestBody.getPhone());
        input.setEmail(requestBody.getEmail());
        input.setCreditCard(requestBody.getCreditCard());
        input.setAddress(requestBody.getAddress());

        try {
            this.createUserUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

}
