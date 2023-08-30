package com.superapp.bill_service.item;

import com.superapp.bill_service.bill.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ItemDto {
    Integer id;
    String code_product;
    Float price;
    Float quantity;
    Bill bill;
}
