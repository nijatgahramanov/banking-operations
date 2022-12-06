package com.compay.msbanking.delegates.error;

import com.compay.msbanking.dto.response.BaseResponse;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ErrorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String errorCode = (String) execution.getVariable("errorCode");
        String errorMessage = (String) execution.getVariable("errorMessage");

        execution.setVariable("response", new BaseResponse().setMessage(errorMessage).setCode(Integer.parseInt(errorCode)));

    }
}
