package com.endava.Supermarket.service;

import com.endava.Supermarket.dto.item.ItemDto;
import com.endava.Supermarket.model.Item;

public interface ItemService {

    Item createItem(ItemDto itemDto);

}
