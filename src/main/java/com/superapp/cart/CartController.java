package com.superapp.cart;

import com.superapp.item.ItemDto;
import com.superapp.product.IProductMapper;
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
    private IProductMapper productMapper;
    @Autowired
    private ICartMapper cartMapper;

    @PutMapping("/{idPerson}")
    public ResponseEntity addItem(@RequestBody ItemDto itemDto, @PathVariable Integer idPerson){
        Cart cart = cartService.getCart(idPerson);
        try {
            cart.addItem(productMapper.productDtoToProduct(itemDto.getProductDto()), itemDto.getQuantity());
            return new ResponseEntity<CartDto>(cartMapper.cartToCartDto(cart), HttpStatus.OK);
        } catch(Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
