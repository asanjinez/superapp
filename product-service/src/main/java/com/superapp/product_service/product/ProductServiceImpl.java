package com.superapp.product_service.product;

import com.superapp.product_service.exception.ExistingIdException;
import com.superapp.product_service.exception.NoProductFoundException;
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
    private IProductJpaDao productJpaDao;

    @Autowired
    private IProductMapper productMapper;


    @Override
    public List<Product> findAll() {
        return productJpaDao.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        try {
            if(productDto.getId() != null && productJpaDao.findById(productDto.getId()).isPresent())
                throw new ExistingIdException("Alredy exists a product with id: " + productDto.getId());

            return productJpaDao.save(productMapper.productDtoToProduct(productDto));

        } catch (ExistingIdException | NoProductFoundException exception) {
            log.info(exception.getMessage(),exception);
            throw exception;
        } catch (Exception exception){
            log.info(exception.getMessage(),exception);
            throw exception;
        }
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
        try {
            return this.productExistsException(id);


        } catch (NoProductFoundException exception) {
            log.info(exception.getMessage(),exception);
            throw exception;

        } catch (Exception exception){
            log.info(exception.getMessage(),exception);
            throw exception;
        }
    }

    @Override
    public Product updateProduct(ProductDto productDto) {
        try {
            Product productToEdit = this.productExistsException(productDto.getId());
            productToEdit.setId(productDto.getId());
            productToEdit.setName(productDto.getName());
            productToEdit.setDescription(productDto.getDescription());
            productToEdit.setUnitPrice(productDto.getUnitPrice());

            return productJpaDao.save(productToEdit);

        } catch (NoProductFoundException  e) {
            log.info(e.getMessage(),e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public Product findByName(String name) {
        try {
            Optional<Product> product = productJpaDao.findByName(name);
            if (!product.isPresent())
                throw new NoProductFoundException("The product with name: " + name + " doesn't exist");

            return product.get();

        } catch (NoProductFoundException  e) {
            log.info(e.getMessage(),e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            Product productToDelete = this.productExistsException(id);
            productJpaDao.delete(productToDelete);

        } catch (NoProductFoundException  e) {
            log.info(e.getMessage(),e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    private Product productExistsException(Integer id){
        Optional<Product> product = productJpaDao.findById(id);
        if (!product.isPresent())
            throw new NoProductFoundException("The product with id: " + id + " doesn't exist");

        return product.get();
    }
}
