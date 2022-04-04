package com.endava.Supermarket.dto.purchase;

import com.endava.Supermarket.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePurchaseDto {

    @NotBlank
    private String supermarketId;

    @NotEmpty
    private List<String> itemIds;

    @NotNull
    private PaymentType paymentType;

    private double cashAmount;

    private LocalDate dateOfPayment;
}
