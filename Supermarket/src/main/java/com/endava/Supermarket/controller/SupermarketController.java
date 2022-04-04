package com.endava.Supermarket.controller;

import com.endava.Supermarket.dto.supermarket.SupermarketDto;
import com.endava.Supermarket.model.Supermarket;
import com.endava.Supermarket.repository.SupermarketRepository;
import com.endava.Supermarket.service.SupermarketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/supermarkets")
public class SupermarketController {

    private final ModelMapper modelMapper;
    private final SupermarketService supermarketService;
    private final SupermarketRepository supermarketRepository;

    public SupermarketController(ModelMapper modelMapper, SupermarketService itemService, SupermarketRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.supermarketService = itemService;
        this.supermarketRepository = itemRepository;
    }

    @PostMapping
    ResponseEntity<SupermarketDto> createSupermarket(@Valid @RequestBody SupermarketDto newSupermarket){
        Supermarket supermarket = supermarketService.createSupermarket(newSupermarket);
        SupermarketDto mappedDto = modelMapper.map(supermarket,SupermarketDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedDto);
    }
}
