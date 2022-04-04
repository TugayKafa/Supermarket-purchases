package com.endava.Supermarket.dto.supermarket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemsToSupermarketDto {

    @NotNull(message = "Please provide supermarketId!")
    private String supermarketId;

    @NotEmpty(message = "You must have at least 1 item!")
    private List<String> itemIds;
}
