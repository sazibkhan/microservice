package com.nagalay.productservice.service;
import com.nagalay.productservice.entity.BrandEntity;
import com.nagalay.productservice.entity.CustomerEntity;
import com.nagalay.productservice.entity.ProductEntity;
import com.nagalay.productservice.repository.BrandRepository;
import com.nagalay.productservice.repository.CustomerRepository;
import com.nagalay.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@AllArgsConstructor
public class EntityValidationService {

    private final ProductRepository productRepository;
    private  final BrandRepository brandRepository;
    private  final CustomerRepository customerRepository;

    public ProductEntity validateProduct(Long id) {
        Objects.requireNonNull(id);
        return productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Product not found with id [%s]", id)));
    }


    public BrandEntity validateBrand(Long id) {
        Objects.requireNonNull(id);
        return brandRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Brand not found with id [%s]", id)));
    }

    public CustomerEntity validateCustomer(Long id) {
        Objects.requireNonNull(id);
        return customerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Customer not found with id [%s]", id)));
    }


}
