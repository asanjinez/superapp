package com.superapp.bill_service.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ItemDto {
    private Integer id;
    private String code_product;
    private Float price;
    private Float quantity;
}
