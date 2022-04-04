package com.endava.Supermarket.dto.supermarket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupermarketDto {

    @NotBlank(message = "Please provide name!")
    @Size(max = 64,message = "Maximum characters for name is 64!")
    private String name;

    @NotBlank(message = "Please provide address!")
    @Size(max = 128,message = "Maximum characters for name is 126!")
    private String address;

    @NotBlank(message = "Please provide phone number!")
    private String phoneNumber;

    @NotBlank(message = "Please provide work hours!")
    private String workHours;
}
