package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.AccountResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.mapper.factory.AccountFactory;
import com.compay.msbanking.mapper.factory.CustomerFactory;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.CustomerFunctionalService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountBusinessService {

    private final AccountFunctionalService accountFunctionalService;
    private final CustomerFunctionalService customerFunctionalService;

    public AccountBusinessService(AccountFunctionalService accountFunctionalService, CustomerFunctionalService customerFunctionalService) {
        this.accountFunctionalService = accountFunctionalService;
        this.customerFunctionalService = customerFunctionalService;
    }

    public List<AccountResponse> getAccounts() {
        List<AccountResponse> response =accountFunctionalService.getAccounts()
                .stream()
                .map((Account account)->(AccountFactory.convertAccountToResponse(account)))
                .collect(Collectors.toList());

        return response;

    }


    public AccountResponse getAccountById(Long id) {
        return  AccountFactory.convertAccountToResponse(accountFunctionalService.getAccountById(id));
    }


    public AccountResponse addAccount(AccountRequest request) {
        Account account = AccountFactory
                .convertRequestToAccount(customerFunctionalService.getCustomerById(request.getCustomerId()),request);
        accountFunctionalService.addAccount(account);
        return AccountFactory.convertAccountToResponse(account);
    }


    public AccountResponse updateAccount(Long id,AccountRequest accountRequest) {
        Account account=AccountFactory.updateAccount(accountFunctionalService.getAccountById(id), accountRequest);
        accountFunctionalService.updateAccount(account);
        return AccountFactory.convertAccountToResponse(account);
    }

    public AccountResponse deleteAccount(Long id){
        Account account = accountFunctionalService.getAccountById(id);
        account.setActive(0);
        accountFunctionalService.updateAccount(account);
        return AccountFactory.convertAccountToResponse(account);
    }



}
