package com.nagalay.profileservice.controller;


import com.nagalay.profileservice.common.ResponseFactory;
import com.nagalay.profileservice.dto.RestResponse;
import com.nagalay.profileservice.dto.request.UserDTO;
import com.nagalay.profileservice.dto.response.ProfileRest;
import com.nagalay.profileservice.dto.response.UserRest;
import com.nagalay.profileservice.entity.UserEntity;
import com.nagalay.profileservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public RestResponse createNewUser(@RequestBody  @Valid UserDTO userDTO ) {
        userService.createUser(userDTO);
        return ResponseFactory.saveSuccess();
    }

    @GetMapping
    public RestResponse getUserList() {
        return ResponseFactory.responseData(userService.getUserList());
    }

    @GetMapping("{id}")
    public UserRest getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


}
