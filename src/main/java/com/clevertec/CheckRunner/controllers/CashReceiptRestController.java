package com.clevertec.CheckRunner.controllers;

import com.clevertec.CheckRunner.models.CashReceipt;
import com.clevertec.CheckRunner.services.CashReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cash-receipt")
public class CashReceiptRestController {

    private final CashReceiptService cashReceiptService;

    @GetMapping("/json")
    public ResponseEntity<CashReceipt> jsonCashReseipt(@RequestParam List<String> products,
                                                       @RequestParam(value = "card", required = false)
                                                       Integer discountCardNumber) {

        return ResponseEntity.ok()
                .body(cashReceiptService.buildReceipt(products, discountCardNumber));
    }



}
