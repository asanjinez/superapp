package com.superapp.product_service.product;

import org.mapstruct.factory.Mappers;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product createProduct(ProductDto productDto);
    public List<Product> createProducts(List<ProductDto> productDtoListList);
    public Product findById(Integer id);
    public Product updateProduct(ProductDto productDto);
    Product findByName(String name);

    public void deleteProduct(Integer id);
}
