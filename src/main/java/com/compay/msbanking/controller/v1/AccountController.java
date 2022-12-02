package com.compay.msbanking.controller.v1;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.service.business.AccountBusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountBusinessService accountService;

    public AccountController(AccountBusinessService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity addAccount(@RequestBody AccountRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse().success(accountService.addAccount(request)));
    }

    @GetMapping
    public ResponseEntity getAccounts(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(accountService.getAccounts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getAccountById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(accountService.getAccountById(id)));
    }

    @DeleteMapping
    public ResponseEntity deleteAccount(@RequestParam("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(accountService.deleteAccount(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAccount(@PathVariable Long id,@RequestBody AccountRequest requestAccount){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(accountService.updateAccount(id,requestAccount)));
    }
}
