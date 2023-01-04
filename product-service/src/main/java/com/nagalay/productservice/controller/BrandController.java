package com.nagalay.productservice.controller;


import com.nagalay.productservice.common.ResponseFactory;
import com.nagalay.productservice.dto.RestResponse;
import com.nagalay.productservice.dto.request.BrandDTO;
import com.nagalay.productservice.dto.response.BrandRest;
import com.nagalay.productservice.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
public class BrandController {

     private  final BrandService brandService;

    @PostMapping
    public RestResponse createNewBrand(@RequestBody BrandDTO brandDTO) {
        brandService.createNewBrand(brandDTO);
        return ResponseFactory.saveSuccess();
    }

    @GetMapping
    public RestResponse getBrandList() {
        return ResponseFactory.responseData(brandService.getBrandList());
    }

    @GetMapping("{id}")
    public BrandRest getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);

    }
    @PutMapping("{id}")
    public RestResponse updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        brandService.updateBarnd(id,brandDTO);
        return  ResponseFactory.updateSuccess();
    }

    @DeleteMapping("{id}")
    public RestResponse deleteBrand(@PathVariable Long id) {
        brandService.deleteBrandById(id);
        return ResponseFactory.deleteSuccess();
    }




}
