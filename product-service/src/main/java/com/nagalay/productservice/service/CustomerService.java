package com.nagalay.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nagalay.productservice.dto.request.CustomerDTO;
import com.nagalay.productservice.dto.response.CustomerRest;
import com.nagalay.productservice.entity.CustomerEntity;
import com.nagalay.productservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityValidationService entityValidationService;

    private final Environment environment;


    public void createNewCustomer(CustomerDTO customerDTO) {
        var customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerRepository.save(customerEntity);
    }


    public List<CustomerRest> getCustomerList() {
        return customerRepository.findAll().stream()
                .map(itm -> {
                    var res = new CustomerRest();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }


    public CustomerRest getCustomerById(Long id) {
        var customerEntity = entityValidationService.validateCustomer(id);
        var response = new CustomerRest();
        BeanUtils.copyProperties(customerEntity, response);
       return  response;
    }


    public void updateCustomer(Long id, CustomerDTO customerDto) {
        var customerEntity = entityValidationService.validateCustomer(id);
        BeanUtils.copyProperties(customerDto, customerEntity);
        customerRepository.save(customerEntity);
    }

    public void deleteCustomerById(Long id) {
        var customerEntity = entityValidationService.validateCustomer(id);
        customerRepository.deleteById(customerEntity.getId());
    }



}
