package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.supermarket.SupermarketDto;
import com.endava.Supermarket.model.Supermarket;
import com.endava.Supermarket.repository.ItemRepository;
import com.endava.Supermarket.repository.SupermarketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupermarketServiceImpl implements SupermarketService{

    private final ModelMapper modelMapper;

    @Autowired
    private SupermarketRepository supermarketRepository;

    public SupermarketServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Supermarket createSupermarket(SupermarketDto newSupermarket) {
        Supermarket supermarket = modelMapper.map(newSupermarket,Supermarket.class);
        if(supermarketRepository.findByName(supermarket.getName()).isPresent()){
            throw new RuntimeException("Supermarket name already in use!");
        }
        supermarket.setId(UUID.randomUUID().toString());
        while (supermarketRepository.findById(UUID.randomUUID().toString()).isPresent()){
            supermarket.setId(UUID.randomUUID().toString());
        }
        return supermarketRepository.save(supermarket);
    }
}
