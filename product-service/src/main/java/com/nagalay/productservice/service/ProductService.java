package com.nagalay.productservice.service;

import com.fasterxml.jackson.databind.introspect.WithMember;
import com.nagalay.productservice.dto.request.ProductDTO;
import com.nagalay.productservice.dto.response.ProductRest;
import com.nagalay.productservice.entity.BrandEntity;
import com.nagalay.productservice.entity.ProductEntity;
import com.nagalay.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final EntityValidationService entityValidationService;

    public void createNewProduct(ProductDTO productDTO) {
        var productEntity = new ProductEntity();
        var brandEntity = entityValidationService.validateBrand(productDTO.getBrandId());
        BeanUtils.copyProperties(productDTO, productEntity);
        productEntity.setBrand(brandEntity);
        productRepository.save(productEntity);
    }



    public List<ProductRest> getProductList() {
            return productRepository.findAll().stream()
                    .map(itm->getProductRest(itm))
                    .sorted(Comparator.comparing(ProductRest::getProductName))
                    .collect(Collectors.toList());
    }
    public ProductRest getProductById(Long id) {
        var productEntity=entityValidationService.validateProduct(id);
        var responce=new ProductRest();
        BeanUtils.copyProperties(productEntity,responce);
        return responce;
    }

    public void updateProduct(Long id,ProductDTO productDTO) {
        var productEntity= entityValidationService.validateProduct(id);
        var brandEntity=entityValidationService.validateBrand(id);
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setBrand(brandEntity);
        productEntity.setPurchasePrice(productDTO.getPurchasePrice());
        productEntity.setSalesPrice(productDTO.getSalesPrice());
        productRepository.save(productEntity);

    }

    public void deleteProductById(Long id){
        var productEntity=entityValidationService.validateProduct(id);
        productRepository.deleteById(id);

    }
    private ProductRest getProductRest(ProductEntity itm){
        var res=new ProductRest();
        BeanUtils.copyProperties(itm,res);
        Optional.ofNullable(itm.getBrand())
                .ifPresent(brand->{
                    res.setBrandId(brand.getId());
                });
        return res;
    }

}
