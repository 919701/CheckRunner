package com.clevertec.CheckRunner.controllers;

import com.clevertec.CheckRunner.print.BasketToString;
import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.services.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/basket")
public class BasketRestController {

    private final BasketService basketService;
    private final BasketToString basketToString;

    @GetMapping("/json")
    public ResponseEntity<Basket> jsonCashReceipt(@RequestParam List<String> products,
                                                  @RequestParam(value = "card", required = false)
                                                  Integer discountCardNumber) {

        return ResponseEntity.ok()
                .body(basketService.buildBasket(products, discountCardNumber));
    }

    @GetMapping(value = "/text")
    public String getCheck(@RequestParam List<String> products,
                           @RequestParam(value = "card", required = false) Integer discountCardNumber) {
        return basketToString.printCheck(basketService.buildBasket(products, discountCardNumber));
    }

}
