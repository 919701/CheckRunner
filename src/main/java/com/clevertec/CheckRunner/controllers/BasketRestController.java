package com.clevertec.CheckRunner.controllers;

import com.clevertec.CheckRunner.models.Basket;
import com.clevertec.CheckRunner.services.BasketService;
import com.clevertec.CheckRunner.utils.mapper.BasketToStringImpl;
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
    private final BasketToStringImpl basketPrintImpl;

    @GetMapping("/json")
    public ResponseEntity<Basket> jsonCashReceipt(@RequestParam(value = "id") List<String> products,
                                                  @RequestParam(value = "card", required = false)
                                                  Integer discountCardNumber) {

        return ResponseEntity.ok()
                .body(basketService.buildBasket(products, discountCardNumber));
    }

    @GetMapping("/text")
    public String getCheck(@RequestParam(value = "id") List<String> products,
                           @RequestParam(value = "card", required = false) Integer discountCardNumber) {
        return basketPrintImpl.printCheck(basketService.buildBasket(products, discountCardNumber));
    }
}
