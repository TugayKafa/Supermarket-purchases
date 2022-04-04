package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.purchase.PurchaseDto;
import com.endava.Supermarket.model.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase createPurchase(PurchaseDto newPurchase);

    List<Purchase> getAll();
}
