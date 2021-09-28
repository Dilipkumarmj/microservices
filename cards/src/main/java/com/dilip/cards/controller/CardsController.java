package com.dilip.cards.controller;

import com.dilip.cards.model.Cards;
import com.dilip.cards.model.Customer;
import com.dilip.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping(value = "/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer){
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }
}
