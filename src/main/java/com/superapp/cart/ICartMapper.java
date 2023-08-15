package com.superapp.cart;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartMapper {
    //TODO
    CartDto cartToCartDto(Cart cart);
    Cart cartDtoToCart(CartDto cartDto);
    List<Cart> cartDtoListToCart(List<CartDto> cartDtoList);
    List<CartDto> cartListToCartDto(List<Cart> cartList);
}
