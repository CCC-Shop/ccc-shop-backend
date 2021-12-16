package com.project.ccc_shop.user.adapter;

import com.project.ccc_shop.user.usecase.create.CreateUserInput;
import com.project.ccc_shop.user.usecase.create.CreateUserOutput;
import com.project.ccc_shop.user.usecase.create.CreateUserUseCase;
import com.project.ccc_shop.user.usecase.get_all.GetAllUserOutput;
import com.project.ccc_shop.user.usecase.get_all.GetAllUserUseCase;
import com.project.ccc_shop.user.usecase.login.LoginUserInput;
import com.project.ccc_shop.user.usecase.login.LoginUserOutput;
import com.project.ccc_shop.user.usecase.login.LoginUserUseCase;
import com.project.ccc_shop.user.usecase.delete.DeleteUserInput;
import com.project.ccc_shop.user.usecase.delete.DeleteUserOutput;
import com.project.ccc_shop.user.usecase.delete.DeleteUserUseCase;
import com.project.ccc_shop.user.usecase.update.UpdateUserInput;
import com.project.ccc_shop.user.usecase.update.UpdateUserOutput;
import com.project.ccc_shop.user.usecase.update.UpdateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    CreateUserUseCase createUserUseCase;
    LoginUserUseCase loginUserUseCase;
    DeleteUserUseCase deleteUserUseCase;
    GetAllUserUseCase getAllUserUseCase;
    UpdateUserUseCase updateUserUseCase;

    @Autowired
    public void setCreateUserUseCase(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Autowired
    public void setLoginUserUseCase(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @Autowired
    public void setDeleteUserUseCase(DeleteUserUseCase deleteUserUseCase) {
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Autowired
    public void setGetAllUserUseCase(GetAllUserUseCase getAllUserUseCase) {
        this.getAllUserUseCase = getAllUserUseCase;
    }

    @Autowired
    public void setUpdateUserUseCase(UpdateUserUseCase updateUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateUserOutput> createUser(@RequestBody CreateUserInput requestBody) {
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
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginUserOutput> loginUser(@RequestBody LoginUserInput requestBody) {
        LoginUserInput input = new LoginUserInput();
        LoginUserOutput output = new LoginUserOutput();

        input.setUsername(requestBody.getUsername());
        input.setPassword(requestBody.getPassword());

        try {
            this.loginUserUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);

        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<DeleteUserOutput> deleteUser(@RequestBody DeleteUserInput requestBody) {
        DeleteUserInput input = new DeleteUserInput();
        DeleteUserOutput output = new DeleteUserOutput();

        input.setId(requestBody.getId());

        try {
            this.deleteUserUseCase.execute(input, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<GetAllUserOutput> getAllUser() {
        GetAllUserOutput output = new GetAllUserOutput();

        try {
            this.getAllUserUseCase.execute(output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UpdateUserOutput> updateUser(@RequestBody UpdateUserInput requestBody) {
        UpdateUserOutput output = new UpdateUserOutput();

        try {
            this.updateUserUseCase.execute(requestBody, output);
            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            output.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(output);
        }
    }
}
