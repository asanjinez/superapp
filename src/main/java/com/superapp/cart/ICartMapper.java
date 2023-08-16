package com.superapp.cart;

import com.superapp.product.IProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartMapper {
    ICartMapper INSTANCE = Mappers.getMapper(ICartMapper.class);
    @Mapping(target = "personDto", expression = "java(com.superapp.person.IPersonMapper.INSTANCE.personToPersonDto(cart.getPerson()))")
    @Mapping(target = "itemDtoList", expression = "java(com.superapp.item.IItemMapper.INSTANCE.itemListToItemDtoList(cart.getItemList()))")
    CartDto cartToCartDto(Cart cart);
    @Mapping(target = "person", expression = "java(com.superapp.person.IPersonMapper.INSTANCE.personDtoToPerson(cartDto.getPersonDto()))")
    @Mapping(target = "itemList", expression = "java(com.superapp.item.IItemMapper.INSTANCE.itemDtoListToItemList(cartDto.getItemDtoList()))")
    Cart cartDtoToCart(CartDto cartDto);
    List<Cart> cartDtoListToCartList(List<CartDto> cartDtoList);
    List<CartDto> cartListToCartDtoList(List<Cart> cartList);
}
