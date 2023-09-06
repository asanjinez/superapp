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
        var products = productJpaDao.findAll();
        log.info("Products found: {} ", products);
        return products;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        try {
            if(productDto.getId() != null && productJpaDao.findById(productDto.getId()).isPresent())
                throw new ExistingIdException("Alredy exists a product with id: " + productDto.getId());

            Product productCreated = productJpaDao.save(productMapper.productDtoToProduct(productDto));
            log.info("Product created: {} ", productCreated);
            return productCreated;

        } catch (ExistingIdException | NoProductFoundException exception) {
            log.error(exception.getMessage(),exception);
            throw exception;
        } catch (Exception exception){
            log.error(exception.getMessage(),exception);
            throw exception;
        }
    }

    @Override
    public List<Product> createProducts(List<ProductDto> productDtoList) {
        List<Product> productList = new ArrayList<Product>();
        for (ProductDto product : productDtoList){
            productList.add(this.createProduct(product));
        }

        log.info("{} Products created", productList.size());
        return productList;
    }

    @Override
    public Product findById(Integer id) {
        try {
            Product product = this.productExistsException(id);
            log.info("Product found: {} ", product);
            return product;


        } catch (NoProductFoundException exception) {
            log.error(exception.getMessage(),exception);
            throw exception;

        } catch (Exception exception){
            log.error(exception.getMessage(),exception);
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

            Product productUpdated = productJpaDao.save(productToEdit);
            log.info("Product updated: {} ", productUpdated);
            return productUpdated;

        } catch (NoProductFoundException  e) {
            log.error(e.getMessage(),e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public Product findByName(String name) {
        try {
            //find product by name or else trhow exception:
            Product product = productJpaDao.findByName(name).orElseThrow(() -> new NoProductFoundException("The product with name: " + name + " doesn't exist"));
            log.info("Product found: {} ", product);
            return product;

        } catch (NoProductFoundException  e) {
            log.error(e.getMessage(),e);
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
            log.info("Product deleted: {} ", productToDelete);

        } catch (NoProductFoundException  e) {
            log.error(e.getMessage(),e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    private Product productExistsException(Integer id){
        Product productFound = productJpaDao.findById(id).orElseThrow(() -> new NoProductFoundException("The product with id: " + id + " doesn't exist"));
        return productFound;
    }
}
