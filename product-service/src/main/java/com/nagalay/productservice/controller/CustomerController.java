package com.nagalay.productservice.controller;


import com.nagalay.productservice.common.ResponseFactory;
import com.nagalay.productservice.dto.RestResponse;
import com.nagalay.productservice.dto.request.CustomerDTO;
import com.nagalay.productservice.dto.response.CustomerRest;
import com.nagalay.productservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public RestResponse createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createNewCustomer(customerDTO);
        return ResponseFactory.saveSuccess();
    }

    @GetMapping
    public RestResponse getCustomerList() {

        return ResponseFactory.responseData(customerService.getCustomerList());
    }

    @GetMapping("{id}")
    public CustomerRest getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @PutMapping("{id}")
    public RestResponse updateCustomer(@PathVariable Long id,
                                       @RequestBody CustomerDTO customerDTO) {

        customerService.updateCustomer(id, customerDTO);
        return ResponseFactory.updateSuccess();
    }

    @DeleteMapping("{id}")
    public RestResponse deleteCustomerById(@PathVariable Long id) {

        customerService.deleteCustomerById(id);
        return ResponseFactory.deleteSuccess();
    }


}
