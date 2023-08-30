package com.superapp.product_service.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    public ProductDto productToProductDto(Product product);
    public Product productDtoToProduct(ProductDto productDto);
    public List<ProductDto> productListToProductDtoList(List<Product> productList);
    public List<Product> productDtoListToProduct(List<ProductDto> productDtoList);
}
