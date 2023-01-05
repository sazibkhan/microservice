package com.nagalay.authenticationservice.controller;

import com.nagalay.authenticationservice.common.ResponseFactory;
import com.nagalay.authenticationservice.dto.RestResponse;
import com.nagalay.authenticationservice.dto.request.RegistrationDTO;
import com.nagalay.authenticationservice.dto.response.RegistrationRest;
import com.nagalay.authenticationservice.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private  final RegistrationService registrationService;

    @PostMapping
    public RestResponse createNewRegistration(@RequestBody RegistrationDTO registrationDTO) {
        registrationService.createNewRegistration(registrationDTO);
        return ResponseFactory.saveSuccess();
    }

    @GetMapping("{id}")
    public RegistrationRest getRegistrationById(@PathVariable Long id) {

        return registrationService.getRegistrationById(id);
    }

    @PutMapping("{id}")
    public RestResponse updateRegistration(@PathVariable Long id, @RequestBody RegistrationDTO registrationDTO) {

        registrationService.updateRegistration(id,registrationDTO);
        return  ResponseFactory.updateSuccess();
    }

    @DeleteMapping("{id}")
    public RestResponse deleteRegistration(@PathVariable Long id) {

        registrationService.deleteRegistrationById(id);
        return ResponseFactory.deleteSuccess();
    }

    @GetMapping
    public RestResponse getRegistrationList() {
        return ResponseFactory.responseData(registrationService.getAllRegistration());
    }




}
