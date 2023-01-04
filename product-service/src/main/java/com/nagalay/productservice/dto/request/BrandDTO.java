package com.nagalay.productservice.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BrandDTO {

    @NotBlank
    @Size(min = 20)
    private String brandName;
}
