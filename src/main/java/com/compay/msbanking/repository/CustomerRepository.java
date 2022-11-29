package com.compay.msbanking.repository;

import com.compay.msbanking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findAllByActive(Integer active);

    Optional<Customer> findByIdAndActive(Long id, Integer active);

    Optional<Customer> findByFin(String fincode);

}
