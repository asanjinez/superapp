package com.superapp.bill;

import com.superapp.item.Item;
import com.superapp.person.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillDto {
    private Integer id;
    private PersonDto personDto;
    private List<Item> items;
    private Float total;
}
