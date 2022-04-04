package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.item.ItemDto;
import com.endava.Supermarket.model.Item;
import com.endava.Supermarket.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    private final ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    public ItemServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Item createItem(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        if(itemRepository.findByName(item.getName()).isPresent()){
            throw new RuntimeException("This item exist in database!");
        }
        item.setId(UUID.randomUUID().toString());
        while(itemRepository.findById(item.getId()).isPresent()){
            item.setId(UUID.randomUUID().toString());
        }
        return itemRepository.save(item);
    }

}
