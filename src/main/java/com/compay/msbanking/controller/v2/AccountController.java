package com.compay.msbanking.controller.v2;

import com.compay.msbanking.dto.request.AccountRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.service.business.AccountBusinessService;
import com.compay.msbanking.util.CamundaUtil;
import org.camunda.bpm.engine.RuntimeService;
import org.glassfish.jersey.internal.guava.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController("accountControllerV2")
@RequestMapping("/api/v2/accounts")
public class AccountController {

    private final AccountBusinessService accountService;
    private final CamundaUtil camundaUtil;
    private final RuntimeService runtimeService;

    public AccountController(AccountBusinessService accountService, CamundaUtil camundaUtil, RuntimeService runtimeService) {
        this.accountService = accountService;
        this.camundaUtil = camundaUtil;
        this.runtimeService = runtimeService;
    }

    @PostMapping
    public BaseResponse addAccount(@RequestBody AccountRequest requestAccount) {
        return camundaUtil.execute("Process_0lwg9qr", requestAccount, runtimeService, BaseResponse.class);
    }

    @GetMapping
    public ResponseEntity<?> getAccounts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(accountService.getAccounts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse().success(accountService.getAccountById(id)));
    }

    @DeleteMapping
    public BaseResponse deleteAccount(@RequestParam("id") Long id) {
        return accountService.deleteAccount(id);
    }

    @PutMapping("/{id}")
    public BaseResponse updateAccount(@PathVariable Long id, @RequestBody AccountRequest request) {
         Map maps = new LinkedHashMap();
         maps.put("id",id);
         maps.put("request",request);

        return camundaUtil.execute("Process_09ncy1r",maps,runtimeService,BaseResponse.class);
    }
}
