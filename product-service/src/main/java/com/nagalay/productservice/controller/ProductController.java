package com.nagalay.productservice.controller;

import com.nagalay.productservice.dto.request.ProductDTO;
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

    public  void createNewProduct(@RequestBody ProductDTO productDTO){
        productService.createNewProduct(productDTO);
    }
    @GetMapping
    public List<ProductRest> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("id")
    public ProductRest getProductById(@PathVariable Long id){
        return  productService.getProductById(id);
    }
    @PutMapping("{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        productService.updateProduct(id,productDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
