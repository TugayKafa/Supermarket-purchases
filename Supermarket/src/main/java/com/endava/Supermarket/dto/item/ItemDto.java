package com.endava.Supermarket.dto.item;

import com.endava.Supermarket.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    @NotBlank(message = "Please provide name!")
    private String name;

    @NotNull(message = "You must provide price!")
    private Double price;

    @NotNull(message = "Please provide type of food!")
    private ItemType type;
}
