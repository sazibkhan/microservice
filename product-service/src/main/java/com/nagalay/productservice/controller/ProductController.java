package com.nagalay.productservice.controller;

import com.nagalay.productservice.common.ResponseFactory;
import com.nagalay.productservice.dto.RestResponse;
import com.nagalay.productservice.dto.request.BrandDTO;
import com.nagalay.productservice.dto.request.ProductDTO;
import com.nagalay.productservice.dto.response.BrandRest;
import com.nagalay.productservice.dto.response.ProductRest;
import com.nagalay.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private  final ProductService productService;
    @PostMapping
    public  RestResponse createNewProduct(@RequestBody ProductDTO productDTO){
        productService.createNewProduct(productDTO);
        return ResponseFactory.saveSuccess();
    }


    @GetMapping
    public RestResponse getProductList() {
        return ResponseFactory.responseData(productService.getProductList());
    }

    @GetMapping("{id}")
    public ProductRest getProductById(@PathVariable Long id){
        return  productService.getProductById(id);
    }

    @PutMapping("{id}")
    public RestResponse updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        productService.updateProduct(id,productDTO);
        return ResponseFactory.updateSuccess();
    }

    @DeleteMapping("{id}")
    public RestResponse deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseFactory.deleteSuccess();
    }

}
