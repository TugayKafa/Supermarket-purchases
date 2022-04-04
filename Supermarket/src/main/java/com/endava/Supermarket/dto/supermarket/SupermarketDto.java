package com.endava.Supermarket.dto.supermarket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupermarketDto {

    @NotBlank(message = "Please provide name!")
    private String name;

    @NotBlank(message = "Please provide address!")
    private String address;

    @NotBlank(message = "Please provide phone number!")
    private String phoneNumber;

    @NotBlank(message = "Please provide work hours!")
    private String workHours;
}
