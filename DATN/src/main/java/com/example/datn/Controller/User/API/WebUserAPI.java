package com.example.datn.Controller.User.API;

import com.example.datn.dto.UserDTO;
import com.example.datn.entity.UserEntity;
import com.example.datn.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Pham Ngoc Son
 * <p>
 * Oct 28, 2021
 */
@RestController
public class WebUserAPI {
    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebUserAPI(IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PutMapping(value = "/profile", produces = "application/json;charset=UTF-8")
    public String updateProfile(@RequestBody UserDTO userDTO) {
        userService.updateProfile(userDTO);
        return "web/profile";
    }

    @PutMapping(value = "/resetPassword", produces = "application/json;charset=UTF-8")
    public void resetPassword(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.findById(userDTO.getId());
        if(bCryptPasswordEncoder.matches(userDTO.getOldPassword(), userEntity.getPassword()) && userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            userService.updatePassword(userEntity, userDTO.getPassword());
        }
    }
}
