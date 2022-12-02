package com.compay.msbanking.service.business;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.request.CustomerRequest;
import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.dto.response.AccountResponse;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.dto.response.CustomerResponse;
import com.compay.msbanking.entity.Account;
import com.compay.msbanking.entity.Customer;
import com.compay.msbanking.enums.AccountStatusEnum;
import com.compay.msbanking.mapper.factory.AccountFactory;
import com.compay.msbanking.mapper.factory.CustomerFactory;
import com.compay.msbanking.service.functional.AccountFunctionalService;
import com.compay.msbanking.service.functional.CustomerFunctionalService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        List<AccountResponse> responseList = accountFunctionalService.getAccounts()
                .stream()
                .map((Account account) -> (AccountFactory.convertAccountToResponse(account)))
                .collect(Collectors.toList());
        response.setData(responseList);

        return response;

    }


    public AccountResponse getAccountById(Long id) {
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(accountFunctionalService.getAccountById(id));
        return accountResponse;
    }


    public AccountResponse addAccount(AccountRequest request) {
        Account account = AccountFactory
                .convertRequestToAccount(customerFunctionalService.getCustomerById(request.getCustomerId()), request);
        accountFunctionalService.addAccount(account);
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(account);

        return accountResponse;
    }


    //transfer zamani lik basda bele bir account var mi yoxlamaq ucundur
    public boolean checkAccountAvailable(Long id) {
        if (accountFunctionalService.getAccountById(id) != null)
            return true;
        return false;
    }

    public boolean checkDebitorAmount(BigDecimal amount) {
        BigDecimal decimal = new BigDecimal(0);
        if (amount.compareTo(decimal) == 1)
            return true;
        return false;
    }

    public boolean checkBalance(BigDecimal amount, TransferRequest request) {
        Account account = accountFunctionalService.getAccountById(request.getDebitorAccountId());
        if (account.getBalance().compareTo(amount) == 1 || account.getBalance().compareTo(amount) == 0)
            return true;
        return false;
    }


    public BaseResponse updateAccount(Long id, AccountRequest accountRequest) {
        BaseResponse response = new BaseResponse();
        Account account = AccountFactory.updateAccount(accountFunctionalService.getAccountById(id), accountRequest);
        accountFunctionalService.updateAccount(account);
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(account);
        response.setData(accountResponse);
        return response;
    }

    public BaseResponse deleteAccount(Long id) {
        BaseResponse response = new BaseResponse();
        Account account = accountFunctionalService.getAccountById(id);
        account.setActive(0);
        accountFunctionalService.updateAccount(account);
        AccountResponse accountResponse = AccountFactory.convertAccountToResponse(account);
        response.setData(accountResponse);
        return response;
    }

}
