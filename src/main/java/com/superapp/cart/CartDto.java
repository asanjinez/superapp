package com.superapp.cart;

import com.superapp.item.ItemDto;
import com.superapp.person.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Integer id;
    private List<ItemDto> itemDtoList = new ArrayList<>();
    private PersonDto personDto;
}
