package com.compay.msbanking.repository;

import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<List<Account>> findAllByActive(Integer active);

    Optional<Account> findByIdAndActive(Long id, Integer active);

}
