package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.supermarket.AddItemsToSupermarketDto;
import com.endava.Supermarket.dto.supermarket.ResponseSupermarket;
import com.endava.Supermarket.dto.supermarket.SupermarketDto;
import com.endava.Supermarket.model.Supermarket;

import java.util.List;

public interface SupermarketService {

    Supermarket createSupermarket(SupermarketDto newSupermarket);

    List<String> addItemsToSupermarket(AddItemsToSupermarketDto addItemsToSupermarketDto);

    ResponseSupermarket getSupermarket(String id);
}
