package com.nagalay.profileservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
public class ProfileDTO {

    private String name;
    private String designation;
    private String address;
}
