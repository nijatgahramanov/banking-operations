//package com.compay.msbanking.delegates.customerDelegate;
//
//import com.compay.msbanking.dto.response.BaseResponse;
//import com.compay.msbanking.entity.Customer;
//import com.compay.msbanking.service.business.CustomerBusinessService;
//import com.compay.msbanking.service.functional.CustomerFunctionalService;
//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.JavaDelegate;
//
//
//
//public class DeleteCustomerDelegate implements JavaDelegate {
//
//    private final CustomerBusinessService businessService;
//    private final CustomerFunctionalService functionalService;
//
//    public DeleteCustomerDelegate(CustomerBusinessService businessService, CustomerFunctionalService functionalService) {
//        this.businessService = businessService;
//        this.functionalService = functionalService;
//    }
//
//    @Override
//    public void execute(DelegateExecution execution) throws Exception {
//        Long id = (Long) execution.getVariable("id");
//        Customer customer = functionalService.getCustomerById(id);
//        customer.setActive(0);
//        functionalService.updateCustomer(customer);
//        BaseResponse response = new BaseResponse();
//        response.setCode(300);
//        response.setMessage("customer successfully deleted");
//        execution.setVariable("response",response);
//    }
//}
