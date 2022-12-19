package com.clevertec.CheckRunner.controllers;

import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.services.DiscountCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/discountcards")
public class DiscountCardRestController {
    private final DiscountCardService discountCardService;

    @GetMapping()
    public ResponseEntity<List<DiscountCard>> allDiscountCards() {
        List<DiscountCard> allDiscountCard = discountCardService.allDiscountCard();
        return allDiscountCard != null
                ? new ResponseEntity<>(allDiscountCard, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{number}")
    public ResponseEntity<DiscountCard> discountCardByNumber(@PathVariable Integer number) {
        DiscountCard numberCard = discountCardService.findByNumberCard(number);
        return numberCard != null
                ? new ResponseEntity<>(numberCard, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
