package com.superapp.product;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    List<ProductDto> productListToProductDtoList(List<Product> productList);
    List<Product> productDtoListToProduct(List<ProductDto> productDtoList);
}
