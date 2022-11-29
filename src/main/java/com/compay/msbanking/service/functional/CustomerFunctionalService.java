package com.compay.msbanking.service.functional;


import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerFunctionalService {

    private final CustomerRepository customerRepository;

    public CustomerFunctionalService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getCustomers() {
        return customerRepository.findAllByActive(1)
                .orElseGet(()->new ArrayList());

    }


    public Customer getCustomerById(Long id) {
        return customerRepository.findByIdAndActive(id, 1)
                .orElseThrow(()->(BaseException.of(ErrorEnum.CUSTOMER_NOT_FOUND)));
    }


    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerFinCode(String fincode){
        return customerRepository.findByFin(fincode).orElse(null);
    }





}
