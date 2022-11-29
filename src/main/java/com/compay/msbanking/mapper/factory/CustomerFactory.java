package com.compay.msbanking.mapper.factory;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.AccountResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.mapper.CustomerMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerFactory {


       public static Customer addCustomer(CustomerRequest request){
           if(!(StringUtils.hasText(request.getLastname())
                   || StringUtils.hasText(request.getLastname())
                   || StringUtils.hasText(request.getFin()))){
               throw BaseException.of(ErrorEnum.INVALID_REQUEST);
           }

           Customer customer = CustomerMapper.INSTANCE.toCustomer(request);
           return customer;
       }
       public static void updateCustomer(Customer customer,CustomerRequest request){
           if(StringUtils.hasText(request.getLastname())){
               customer.setLastname(request.getLastname());
           }
           if(StringUtils.hasText(request.getFirstname())){
               customer.setFirstname(request.getFirstname());
           }
           if(StringUtils.hasText(request.getAddress())){
               customer.setAddress(request.getAddress());
           }
           if(StringUtils.hasText(request.getFin())){
               customer.setFin(request.getFin());
           }
           if(StringUtils.hasText(request.getLastname())){
               customer.setLastname(request.getLastname());
           }
           if(StringUtils.hasText(request.getFatherName())){
               customer.setFatherName(request.getFatherName());
           }
           if(request.getAge()!=null){
               customer.setAge(request.getAge());
           }
           if(StringUtils.hasText(request.getCustomerType().toString())){
               customer.setCustomerType(request.getCustomerType());
           }

       }
    public static CustomerResponse convertCustomerToResponse(Customer customer){
           List<AccountResponse> accountResponses = new ArrayList<>();
           if(customer.getAccounts()!=null) {
               accountResponses = customer.getAccounts()
                       .stream()
                       .map((Account account) -> (AccountFactory.convertAccountToResponse(account)))
                       .collect(Collectors.toList());
           }

        return new CustomerResponse().setCustomerType(customer.getCustomerType())
                .setAge(customer.getAge())
                .setAddress((customer.getAddress()))
                .setFin(customer.getFin())
                .setFatherName(customer.getFatherName())
                .setFirstname(customer.getFirstname())
                .setLastname(customer.getLastname())
                .setId(customer.getId())
                .setAccountResponse(accountResponses);
    }
}
