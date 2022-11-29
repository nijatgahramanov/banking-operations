package com.compay.msbanking.service.functional;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.mapper.AccountMapper;
import com.compay.msbanking.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountFunctionalService {


    private final AccountRepository accountRepository;
    public AccountFunctionalService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAllByActive(1)
                .orElseGet(()->(new ArrayList()));
    }

    public Account getAccountById(Long id) {
        return accountRepository.findByIdAndActive(id,1)
                .orElseThrow(()->(BaseException.of(ErrorEnum.ACCOUNT_NOT_FOUND)));
    }

    public Account addAccount(Account account) {
        accountRepository.save(account);
        return account;
    }


    public Account updateAccount(Account account) {

        return accountRepository.save(account);
    }

}
