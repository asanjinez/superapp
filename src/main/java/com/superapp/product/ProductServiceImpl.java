package com.superapp.product;

import com.superapp.exception.ExistingNameException;
import com.superapp.exception.NoPersonFoundException;
import com.superapp.exception.NoProductFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductJpaDao productJpaDao;

    @Autowired
    IProductMapper productMapper;


    @Override
    public List<Product> findAll() {
        return productJpaDao.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        if(productDto.getId() != null && productJpaDao.findById(productDto.getId()).isPresent())
            throw new ExistingNameException("Alredy exists a product with id " + productDto.getId());

        return productJpaDao.save(productMapper.productDtoToProduct(productDto));
    }

    @Override
    public List<Product> createProducts(List<ProductDto> productDtoList) {
        List<Product> productList = new ArrayList<Product>();
        for (ProductDto product : productDtoList){
            productList.add(this.createProduct(product));
        }
        return productList;
    }

    @Override
    public Product findById(Integer id) {
        return this.productExistsException(id);
    }

    @Override
    public Product updateProduct(ProductDto productDto) {
        Product productToEdit = this.productExistsException(productDto.getId());
        productToEdit.setId(productDto.getId());
        productToEdit.setName(productDto.getName());
        productToEdit.setDescription(productDto.getDescription());
        productToEdit.setUnitPrice(productDto.getUnitPrice());

        return productJpaDao.save(productToEdit);
    }

    @Override
    public Product findByName(String name) {
        Optional<Product> product = productJpaDao.findByName(name);
        if (!product.isPresent())
            throw new NoProductFoundException("This product doesn't exist");
        return product.get();
    }
    @Override
    public void deleteProduct(Integer id) {
        Product productToDelete = this.productExistsException(id);
        productJpaDao.delete(productToDelete);

    }

    private Product productExistsException(Integer id){
        Optional<Product> product = productJpaDao.findById(id);
        if (!product.isPresent())
            throw new NoProductFoundException("This product doesn't exist");

        return product.get();
    }
}
