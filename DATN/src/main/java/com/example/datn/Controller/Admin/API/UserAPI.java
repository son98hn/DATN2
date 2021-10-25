package com.example.datn.Controller.Admin.API;

import com.example.datn.dto.UserDTO;
import com.example.datn.service.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPI {
    private final IUserService userService;

    public UserAPI(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api-admin-user", produces = "application/json;charset=UTF-8")
    public void createUser(@RequestBody UserDTO model) {
        userService.saveUser(model);
    }

    @PutMapping(path = "/api-admin-user", produces = "application/json;charset=UTF-8")
    public void updateUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
    }


    @DeleteMapping(value = "/api-admin-user", produces = "application/json;charset=UTF-8")
    public void deleteUser(@RequestBody long[] ids) {
        userService.delete(ids);
    }

    @PutMapping(value = "api-admin-resetuser")
    public void resetPassword(@RequestBody long[] ids) {
        userService.resetPassword(ids);
    }
}
