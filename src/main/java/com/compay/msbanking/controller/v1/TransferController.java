package com.compay.msbanking.controller.v1;

import com.compay.msbanking.service.business.TransferBusinessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfer")
public class TransferController {

    private final TransferBusinessService businessService;


    public TransferController(TransferBusinessService businessService) {
        this.businessService = businessService;
    }
}
