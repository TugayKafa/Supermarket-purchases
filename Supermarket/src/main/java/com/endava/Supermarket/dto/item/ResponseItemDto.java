package com.endava.Supermarket.dto.item;

import com.endava.Supermarket.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemDto {
    private String id;

    private String name;

    private Double price;

    private Type type;

}
