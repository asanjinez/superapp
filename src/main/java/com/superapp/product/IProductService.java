package com.superapp.product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> findAll();
    public Product createProduct(ProductDto productDto);
    public List<Product> createProdcuts(List<ProductDto> productDtoListList);
    public Product findById(Integer id);
    public Product updateProduct(ProductDto productDto);
    Product findByName(String name);

    public void deleteProduct(Integer id);
}
