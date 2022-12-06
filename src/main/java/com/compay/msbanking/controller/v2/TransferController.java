package com.compay.msbanking.controller.v2;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.dto.response.BaseResponse;
import com.compay.msbanking.util.CamundaUtil;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("transferControllerV2")
@RequestMapping("/api/v2/transfers")
public class TransferController {
    private final CamundaUtil camundaUtil;
    private final RuntimeService runtimeService;

    public TransferController(CamundaUtil camundaUtil, RuntimeService runtimeService) {
        this.camundaUtil = camundaUtil;
        this.runtimeService = runtimeService;
    }


    @PostMapping
    public BaseResponse doTransfer(@RequestBody TransferRequest request) {
        return camundaUtil.execute("DoTransfer", request, runtimeService, BaseResponse.class);
    }
}
