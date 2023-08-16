package com.superapp.item;

import com.superapp.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Integer id;
    private ProductDto productDto;
    private Float quantity;
    private Float total;
}
