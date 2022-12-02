package com.compay.msbanking.service.functional;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.enums.AccountStatusEnum;
import com.compay.msbanking.enums.ErrorEnum;
import com.compay.msbanking.exception.BaseException;
import com.compay.msbanking.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AccountFunctionalService {
    Random random = new Random();


    private final AccountRepository accountRepository;

    public AccountFunctionalService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAllByActive(1)
                .orElseGet(() -> (new ArrayList()));
    }

    public Account getAccountById(Long id) {
        return accountRepository.findByIdAndActive(id, 1)
                .orElseThrow(() -> (BaseException.of(ErrorEnum.ACCOUNT_NOT_FOUND)));
    }

    public Account addAccount(Account account) {
        account.setIban(generateIbanNumber());
        account.setNumber(generateAccountNumber());
        accountRepository.save(account);
        return account;
    }

    private String generateIbanNumber() {
        StringBuilder ibanNumber = new StringBuilder(28);
        ibanNumber.append("AZ");
        for (int i = 0; i < 26; i++) {
            ibanNumber.append(random.nextInt(9));
        }
        return ibanNumber.toString();
    }

    //Account statusu yoxlamaq ucundur
    public boolean checkAccountStatus(Account account) {
        if (account.getStatus().ordinal() != AccountStatusEnum.BLOCKED.ordinal())
            return true;
        return false;
    }


    private String generateAccountNumber() {
        StringBuilder accNumber = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            accNumber.append(random.nextInt(9));
        }
        return accNumber.toString();
    }


    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

}
