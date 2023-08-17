package com.superapp.cart;

import com.superapp.exception.NoProductFoundException;
import com.superapp.item.ItemDto;
import com.superapp.product.IProductMapper;
import com.superapp.product.IProductService;
import com.superapp.product.Product;
import com.superapp.product.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductMapper productMapper;
    @Autowired
    private ICartMapper cartMapper;

    @PostMapping("/{idPerson}")
    public ResponseEntity addItem(@RequestBody ItemDto itemDto, @PathVariable Integer idPerson){
        Cart cart = cartService.getCart(idPerson);
        try {
            Product product = productService.findById(itemDto.getIdProduct());
            Integer idCart = cart.getId();
            ProductDto productDto = productMapper.productToProductDto(product);
            Float quantity = itemDto.getQuantity();
            cart = cartService.addItem(idCart, productDto, quantity);
            return new ResponseEntity<CartDto>(cartMapper.cartToCartDto(cart), HttpStatus.OK);
        } catch(NoProductFoundException e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idPerson}")
    public ResponseEntity removeItem(@RequestBody ItemDto itemDto, @PathVariable Integer idPerson){
        Cart cart = cartService.getCart(idPerson);
        try {
            cart = cartService.deleteItem(cart.getId(), itemDto.getId());
            return new ResponseEntity<CartDto>(cartMapper.cartToCartDto(cart), HttpStatus.OK);
        } catch(Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idPerson}/all")
    public ResponseEntity removeAllItems(@PathVariable Integer idPerson){
        Cart cart = cartService.getCart(idPerson);
        try {
            cart = cartService.deleteAllItems(cart.getId());
            return new ResponseEntity<CartDto>(cartMapper.cartToCartDto(cart), HttpStatus.OK);
        } catch(Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
