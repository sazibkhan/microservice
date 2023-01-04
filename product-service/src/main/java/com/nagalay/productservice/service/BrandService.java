package com.nagalay.productservice.service;

import com.nagalay.productservice.dto.request.BrandDTO;
import com.nagalay.productservice.dto.response.BrandRest;
import com.nagalay.productservice.entity.BrandEntity;
import com.nagalay.productservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;
    private final EntityValidationService entityValidationService;
    private final Environment environment;


    public void createNewBrand(BrandDTO brandDto) {
        var brandEntity= new BrandEntity();
        BeanUtils.copyProperties(brandDto,brandEntity);
        brandRepository.save(brandEntity);

    }


    public BrandRest getBrandById(long id){
        var brandEntity=entityValidationService.validateBrand(id);
        var response =new BrandRest();
        BeanUtils.copyProperties(brandEntity,response);
        return response;
    }


    public void updateBarnd(Long id, BrandDTO brandDto) {
        var brandEntity=entityValidationService.validateBrand(id);
        brandEntity.setBrandName(brandDto.getBrandName());
        brandRepository.save(brandEntity);

    }

    public void deleteBrandById(Long id) {
        var brandEntity = entityValidationService.validateBrand(id);
        brandRepository.deleteById(brandEntity.getId());

    }


    public List<BrandRest> getBrandList() {
        return brandRepository.findAll().stream()
                .map(itm -> {
                    var res = new BrandRest();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }



}
