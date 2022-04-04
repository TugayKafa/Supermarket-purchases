package com.endava.Supermarket.dto.item;

import com.endava.Supermarket.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    @NotBlank(message = "Please provide name!")
    @Size(max = 64,message = "Maximum characters for name is 64!")
    private String name;

    @NotNull(message = "You must provide price!")
    @Min(value = 0)
    @Max(value = 10000)
    private Double price;

    @NotNull(message = "Please provide type of food!")
    private ItemType type;
}
