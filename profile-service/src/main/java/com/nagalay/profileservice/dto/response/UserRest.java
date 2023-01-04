package com.nagalay.profileservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {

    private Long id;
    private String name;

    private String email;
    private String mobile;
    private String gender;
    private int age;
    private String nationality;

}
