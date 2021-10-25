package com.example.datn.Controller.User.API;

import com.example.datn.dto.UserDTO;
import com.example.datn.service.impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Pham Ngoc Son
 * <p>
 * Oct 12, 2021
 */
@RestController
public class RegisterAPI {
    private final
    UserService userService;

    public RegisterAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    private String signupSave(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);

        return "web/register";
    }
}
