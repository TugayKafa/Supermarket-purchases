package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.purchase.PurchaseDto;
import com.endava.Supermarket.exception.InvalidPurchaseMethod;
import com.endava.Supermarket.model.Item;
import com.endava.Supermarket.model.PaymentType;
import com.endava.Supermarket.model.Purchase;
import com.endava.Supermarket.model.Supermarket;
import com.endava.Supermarket.repository.ItemRepository;
import com.endava.Supermarket.repository.PurchaseRepository;
import com.endava.Supermarket.repository.SupermarketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SupermarketRepository supermarketRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Purchase createPurchase(PurchaseDto newPurchase) {
        Purchase purchase = modelMapper.map(newPurchase,Purchase.class);
        purchase.setItems(new ArrayList<>());
        purchase.setSupermarket(supermarketRepository.findById(newPurchase.getSupermarketId()).orElseThrow(RuntimeException::new));
        if (newPurchase.getPaymentType().equals(PaymentType.CASH) && newPurchase.getCashAmount()==null){
            throw new InvalidPurchaseMethod();
        }
        for (String itemId : newPurchase.getItemIds()) {
            Item item = itemRepository.findById(itemId).orElse(null);
            if(item!=null&&purchase.getSupermarket().getItems().contains(item)){
                purchase.getItems().add(item);
            }
        }
        if(purchase.getItems().size()<=0) throw new RuntimeException("In this supermarket does not have items to buy");
        purchase.setId(UUID.randomUUID().toString());
        while (purchaseRepository.findById(UUID.randomUUID().toString()).isPresent()) {
            purchase.setId(UUID.randomUUID().toString());
        }
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }
}
