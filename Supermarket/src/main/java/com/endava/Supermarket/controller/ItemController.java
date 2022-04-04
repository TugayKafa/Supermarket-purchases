package com.endava.Supermarket.controller;

import com.endava.Supermarket.dto.item.ItemDto;
import com.endava.Supermarket.model.Item;
import com.endava.Supermarket.repository.ItemRepository;
import com.endava.Supermarket.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ModelMapper modelMapper;
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    public ItemController(ModelMapper modelMapper, ItemService itemService, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto newItem) {
        Item item = itemService.createItem(newItem);
        ItemDto mappedDto = modelMapper.map(item, ItemDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(mappedDto);
    }
}
