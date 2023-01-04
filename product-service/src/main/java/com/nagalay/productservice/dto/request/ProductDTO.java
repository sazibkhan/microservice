package com.nagalay.productservice.dto.request;

import lombok.Data;

@Data
public class ProductDTO {

    private String productName;
    private Double purchasePrice;
    private Double salesPrice;
    private Long brandId;
}
