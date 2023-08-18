package com.superapp.product;

import com.superapp.exception.ExistingIdException;
import com.superapp.exception.NoProductFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    IProductMapper productMapper;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDto productDto) {
        try {
            ProductDto productCreated = productMapper.productToProductDto(productService.createProduct(productDto));
            return new ResponseEntity<ProductDto>(productCreated, HttpStatus.CREATED);

        } catch (ExistingIdException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/some")
    public ResponseEntity createSomeProducts(@RequestBody List<ProductDto> productDtoList) {
        try {
            List<ProductDto> productsCreated = productMapper.productListToProductDtoList(this.productService.createProducts(productDtoList));
            return new ResponseEntity<List<ProductDto>>(productsCreated, HttpStatus.CREATED);

        } catch (ExistingIdException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            List<ProductDto> products = productMapper.productListToProductDtoList(productService.findAll());
            return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        try {
            ProductDto productFound = productMapper.productToProductDto(productService.findById(id));
            return new ResponseEntity<ProductDto>(productFound, HttpStatus.FOUND);

        } catch (NoProductFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto) {
        try {
            ProductDto productUpdated = productMapper.productToProductDto(productService.updateProduct(productDto));
            return new ResponseEntity<ProductDto>(productUpdated, HttpStatus.OK);

        } catch (NoProductFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestBody Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NoProductFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

