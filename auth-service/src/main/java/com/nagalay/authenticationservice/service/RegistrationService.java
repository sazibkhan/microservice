package com.nagalay.authenticationservice.service;


import com.nagalay.authenticationservice.dto.request.RegistrationDTO;
import com.nagalay.authenticationservice.dto.response.RegistrationRest;
import com.nagalay.authenticationservice.entity.RegistrationEntity;
import com.nagalay.authenticationservice.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EntityValidationService entityValidationService;


    public void createNewRegistration(RegistrationDTO registrationDTO) {

        var registrationEntity= new RegistrationEntity();
        BeanUtils.copyProperties(registrationDTO,registrationEntity);
        registrationRepository.save(registrationEntity);
    }


    public RegistrationRest getRegistrationById(long id){

        var registrationEntity=entityValidationService.validateRegistration(id);
        var response =new RegistrationRest();
        BeanUtils.copyProperties(registrationEntity,response);
        return response;
    }


    public void updateRegistration(Long id, RegistrationDTO registrationDTO) {

        var registrationEntity=entityValidationService.validateRegistration(id);
        registrationEntity.setUsername(registrationDTO.getUsername());
        registrationEntity.setEmail(registrationDTO.getEmail());
        registrationEntity.setPassword(registrationDTO.getPassword());
        registrationEntity.setConfirmPassword(registrationDTO.getConfirmPassword());
        registrationEntity.setGender(registrationDTO.getGender());
        registrationEntity.setPhone(registrationDTO.getPhone());
        registrationRepository.save(registrationEntity);

    }

    public void deleteRegistrationById(Long id) {

        var userAccount=entityValidationService.validateRegistration(id);
        registrationRepository.deleteById(userAccount.getId());

    }

    public List<RegistrationRest> getAllRegistration() {

        return registrationRepository.findAll().stream()
                .map(itm -> {
                    var res = new RegistrationRest();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }


}
