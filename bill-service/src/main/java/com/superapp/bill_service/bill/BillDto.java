package com.superapp.bill_service.bill;

import com.superapp.bill_service.item.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillDto {
    private Integer id;
    private List<ItemDto> dtoItems;
    private Float total;
}
