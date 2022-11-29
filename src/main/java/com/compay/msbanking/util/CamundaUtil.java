package com.compay.msbanking.util;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class CamundaUtil {

    public <T> T execute(String processKey, Serializable request, RuntimeService runtimeService, Class<T> responseObject){
        return startProcess(processKey, request, runtimeService)
                .getVariables()
                .getValue("response",responseObject);
    }
    private ProcessInstanceWithVariables startProcess(String processKey, Serializable request, RuntimeService runtimeService){
        return runtimeService.createProcessInstanceByKey(processKey)
                .setVariable("request", request)
                .executeWithVariablesInReturn();
    }

    private ProcessInstanceWithVariables startProcess(String processKey, Map maps, RuntimeService runtimeService){
        return runtimeService.createProcessInstanceByKey(processKey)
                .setVariables(maps)
                .executeWithVariablesInReturn();
    }

    public <T> T execute(String processKey,Map maps, RuntimeService runtimeService, Class<T> responseObject){
        return startProcess(processKey, maps, runtimeService)
                .getVariables()
                .getValue("response",responseObject);
    }

}
