package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.supermarket.SupermarketDto;
import com.endava.Supermarket.model.Supermarket;

public interface SupermarketService {

    Supermarket createSupermarket(SupermarketDto newSupermarket);

}
