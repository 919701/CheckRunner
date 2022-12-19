package com.clevertec.CheckRunner.services;

import com.clevertec.CheckRunner.models.CashReceipt;

import java.util.List;

public interface CashReceiptService {

    CashReceipt buildReceipt(String[] args);
    CashReceipt buildReceipt(List<String> item, Integer discountCardNumber);

}
