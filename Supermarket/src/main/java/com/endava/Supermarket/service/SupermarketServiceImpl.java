package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.supermarket.AddItemsToSupermarketDto;
import com.endava.Supermarket.dto.supermarket.ResponseSupermarket;
import com.endava.Supermarket.dto.supermarket.SupermarketDto;
import com.endava.Supermarket.model.Item;
import com.endava.Supermarket.model.Supermarket;
import com.endava.Supermarket.repository.ItemRepository;
import com.endava.Supermarket.repository.SupermarketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SupermarketServiceImpl implements SupermarketService {

    private final ModelMapper modelMapper;

    @Autowired
    private SupermarketRepository supermarketRepository;

    @Autowired
    private ItemRepository itemRepository;

    public SupermarketServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Supermarket createSupermarket(SupermarketDto newSupermarket) {
        Supermarket supermarket = modelMapper.map(newSupermarket, Supermarket.class);
        if (supermarketRepository.findByName(supermarket.getName()).isPresent()) {
            throw new RuntimeException("Supermarket name already in use!");
        }
        supermarket.setId(UUID.randomUUID().toString());
        while (supermarketRepository.findById(UUID.randomUUID().toString()).isPresent()) {
            supermarket.setId(UUID.randomUUID().toString());
        }
        return supermarketRepository.save(supermarket);
    }

    @Override
    public List<String> addItemsToSupermarket(AddItemsToSupermarketDto addItemsToSupermarketDto) {
        if (supermarketRepository.findById(addItemsToSupermarketDto.getSupermarketId()).isEmpty()) {
            throw new RuntimeException("Supermarket does not exist!");
        }
        Supermarket supermarket = supermarketRepository.findById(addItemsToSupermarketDto.getSupermarketId()).get();
        List<String> itemIds = addItemsToSupermarketDto.getItemIds();
        List<String> addedItemsNames = new ArrayList<>();
        for (String itemId : itemIds) {
            if (itemRepository.findById(itemId).isPresent()) {
                Item item = itemRepository.findById(itemId).get();
                supermarket.getItems().add(item);
                addedItemsNames.add(item.getName());
            }
        }
        supermarketRepository.save(supermarket);
        return addedItemsNames;
    }

    @Override
    public ResponseSupermarket getSupermarket(String id) {
        Supermarket supermarket = supermarketRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(supermarket,ResponseSupermarket.class);
    }
}
