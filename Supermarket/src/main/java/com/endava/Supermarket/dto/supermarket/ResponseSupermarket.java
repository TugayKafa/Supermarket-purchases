package com.endava.Supermarket.dto.supermarket;

import com.endava.Supermarket.dto.item.ResponseItemDto;
import com.endava.Supermarket.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSupermarket {

    private String name;

    private String address;

    private String phoneNumber;

    private String workHours;

    List<ResponseItemDto> items;
}
