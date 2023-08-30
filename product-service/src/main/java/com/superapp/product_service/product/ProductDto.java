package com.superapp.product_service.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Float unitPrice;
}
