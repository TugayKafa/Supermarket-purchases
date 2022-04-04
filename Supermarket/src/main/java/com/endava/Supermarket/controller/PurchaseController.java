package com.endava.Supermarket.controller;

import com.endava.Supermarket.dto.purchase.PurchaseDto;
import com.endava.Supermarket.dto.purchase.ResponsePurchaseDto;
import com.endava.Supermarket.model.Item;
import com.endava.Supermarket.model.Purchase;
import com.endava.Supermarket.service.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final ModelMapper modelMapper;
    private final PurchaseService purchaseService;

    public PurchaseController(ModelMapper modelMapper, PurchaseService purchaseService) {
        this.modelMapper = modelMapper;
        this.purchaseService = purchaseService;
    }

    @PostMapping
    ResponseEntity<ResponsePurchaseDto> buyItemsFromSupermarket(@Valid @RequestBody PurchaseDto newPurchase){
        Purchase purchase = purchaseService.createPurchase(newPurchase);
        ResponsePurchaseDto mappedDto = modelMapper.map(purchase,ResponsePurchaseDto.class);
        mappedDto.setSupermarketId(purchase.getSupermarket().getId().toString());
        mappedDto.setItemIds(purchase.getItems().stream().map(item->item.getId().toString()).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedDto);
    }

    @GetMapping
    ResponseEntity<List<ResponsePurchaseDto>> getAll(){
        List<ResponsePurchaseDto> all = purchaseService.getAll().stream()
                .map(purchase-> modelMapper.map(purchase,ResponsePurchaseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
