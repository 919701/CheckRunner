package com.clevertec.CheckRunner.controllers;

import com.clevertec.CheckRunner.models.DiscountCard;
import com.clevertec.CheckRunner.services.DiscountCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{number}")
    public ResponseEntity<DiscountCard> deleteDiscountCardByNumberCard(@PathVariable Integer number) {
        final boolean deleted = discountCardService.deleteDiscountCardByNumber(number);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
