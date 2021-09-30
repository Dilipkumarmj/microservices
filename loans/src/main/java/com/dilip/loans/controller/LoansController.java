package com.dilip.loans.controller;

import com.dilip.loans.config.LoansServiceConfig;
import com.dilip.loans.model.Customer;
import com.dilip.loans.model.Loans;
import com.dilip.loans.model.Properties;
import com.dilip.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansServiceConfig loansServiceConfig;

    @PostMapping(value = "/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer){
        return loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
    }

    @GetMapping("/loan/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansServiceConfig.getMsg(),loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(),loansServiceConfig.getActiveBranches());
        String str = ow.writeValueAsString(properties);
        return str;
    }
}
