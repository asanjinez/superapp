package com.superapp.product_service.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products") // Base path for product-related endpoints
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductMapper productMapper;

    /**
     * Create a new product.
     * @param productDto The product data to create.
     * @return ResponseEntity containing the created product or an error message.
     */
    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody ProductDto productDto) {
            ProductDto productCreated = productMapper.productToProductDto(productService.createProduct(productDto));
            return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    /**
     * Create multiple products.
     * @param productDtoList List of product data to create.
     * @return ResponseEntity containing the created products or an error message.
     */
    @PostMapping("/create/batch")
    public ResponseEntity createSomeProducts(@RequestBody List<ProductDto> productDtoList) {
            List<ProductDto> productsCreated = productMapper.productListToProductDtoList(this.productService.createProducts(productDtoList));
            return new ResponseEntity<>(productsCreated, HttpStatus.CREATED);
    }

    /**
     * Get a list of all products.
     * @return ResponseEntity containing the list of products or an error message.
     */
    @GetMapping("/list")
    public ResponseEntity findAll() {
            List<ProductDto> products = productMapper.productListToProductDtoList(productService.findAll());
            return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get a product by its ID.
     * @param id The ID of the product.
     * @return ResponseEntity containing the product or an error message.
     */
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
            ProductDto productFound = productMapper.productToProductDto(productService.findById(id));
            return new ResponseEntity<>(productFound, HttpStatus.FOUND);
    }

    /**
     * Update an existing product.
     * @param productDto The updated product data.
     * @return ResponseEntity containing the updated product or an error message.
     */
    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto) {
            ProductDto productUpdated = productMapper.productToProductDto(productService.updateProduct(productDto));
            return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

    /**
     * Delete a product by its ID.
     * @param id The ID of the product to delete.
     * @return ResponseEntity indicating success or an error message.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
