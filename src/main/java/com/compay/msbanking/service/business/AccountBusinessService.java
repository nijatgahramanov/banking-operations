package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.response.AccountResponse;
import com.compay.msbanking.dto.response.BaseResponse;
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

    public BaseResponse getAccounts() {
        BaseResponse response = new BaseResponse();
        List<AccountResponse> responseList =accountFunctionalService.getAccounts()
                .stream()
                .map((Account account)->(AccountFactory.convertAccountToResponse(account)))
                .collect(Collectors.toList());
        response.setData(responseList);

        return response;

    }


    public BaseResponse getAccountById(Long id) {
        BaseResponse response = new BaseResponse();
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(accountFunctionalService.getAccountById(id));
        response.setData(accountResponse);
        return response;
    }


    public BaseResponse addAccount(AccountRequest request) {
        BaseResponse response = new BaseResponse();
        Account account = AccountFactory
                .convertRequestToAccount(customerFunctionalService.getCustomerById(request.getCustomerId()),request);
        accountFunctionalService.addAccount(account);
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(account);
        response.setData(accountResponse);
        return response;
    }


    public BaseResponse updateAccount(Long id, AccountRequest accountRequest) {
        BaseResponse response = new BaseResponse();
        Account account=AccountFactory.updateAccount(accountFunctionalService.getAccountById(id), accountRequest);
        accountFunctionalService.updateAccount(account);
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(account);
        response.setData(accountResponse);
        return response;
    }

    public BaseResponse deleteAccount(Long id){
        BaseResponse response = new BaseResponse();
        Account account = accountFunctionalService.getAccountById(id);
        account.setActive(0);
        accountFunctionalService.updateAccount(account);
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(account);
        response.setData(accountResponse);
        return response;
    }



}
