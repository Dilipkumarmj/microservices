package com.dilip.cards.controller;

import com.dilip.cards.config.CardsServiceConfig;
import com.dilip.cards.model.Cards;
import com.dilip.cards.model.Customer;
import com.dilip.cards.model.Properties;
import com.dilip.cards.repository.CardsRepository;
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
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;

    @PostMapping(value = "/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer){
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/card/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(),cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),cardsServiceConfig.getActiveBranches());
        String str = ow.writeValueAsString(properties);
        return str;
    }
}
