package com.superapp.product;

import com.superapp.person.IPersonMapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface IProductService {
    IProductService INSTANCE = Mappers.getMapper(IProductService.class);
    public List<Product> findAll();
    public Product createProduct(ProductDto productDto);
    public List<Product> createProducts(List<ProductDto> productDtoListList);
    public Product findById(Integer id);
    public Product updateProduct(ProductDto productDto);
    Product findByName(String name);

    public void deleteProduct(Integer id);
}
