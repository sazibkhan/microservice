package com.nagalay.profileservice.service;

import com.nagalay.profileservice.dto.request.UserDTO;
import com.nagalay.profileservice.dto.response.ProfileRest;
import com.nagalay.profileservice.dto.response.UserRest;
import com.nagalay.profileservice.entity.UserEntity;
import com.nagalay.profileservice.exception.UserNotFoundException;
import com.nagalay.profileservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private EntityValidationService entityValidationService;

    public void createUser(UserDTO userDTO) {
        var userEntity=new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        userRepository.saveAndFlush(userEntity);
    }

    public List<UserRest> getUserList() {
        return userRepository.findAll().stream()
                .map(itm->{
                    var res=new UserRest();
                    BeanUtils.copyProperties(itm,res);
                    return res;
                }).collect(Collectors.toList());

    }


    public UserRest getUserById(Long id) {
        var userEntity=entityValidationService.validateUser(id);
        var response=new UserRest();
        BeanUtils.copyProperties(userEntity,response);
        return response;
    }



}
