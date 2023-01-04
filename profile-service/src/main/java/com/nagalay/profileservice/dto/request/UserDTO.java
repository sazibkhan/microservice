package com.nagalay.profileservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "username shouldn't be null")
    private String name;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "invalid email address")
    @Column(unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Pattern(regexp = "\\d{11}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",message = "invalid Mobile Number Entered ")
    private String mobile;

    private String gender;

    @Min(18)
    @Max(60)
    private int age;

    @NotBlank
    private String nationality;



}
