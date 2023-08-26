package com.superapp.product;

import com.superapp.item.IItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    List<ProductDto> productListToProductDtoList(List<Product> productList);
    List<Product> productDtoListToProduct(List<ProductDto> productDtoList);
}
