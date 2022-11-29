package com.compay.msbanking.mapper;

import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toCustomerResponse(Customer customer);

    Customer toCustomer(CustomerRequest customerRequest);


    List<CustomerResponse> toCustomerResponseList(List<Customer> customers);

}
