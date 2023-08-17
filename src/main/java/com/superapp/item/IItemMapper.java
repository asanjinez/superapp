package com.superapp.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IItemMapper {
    IItemMapper INSTANCE = Mappers.getMapper(IItemMapper.class);
    @Mapping(target = "idProduct", expression = "java(item.getProduct().getId())")
    ItemDto itemToItemDto(Item item);
    @Mapping(target = "product", expression = "java(com.superapp.product.IProductService.INSTANCE.findById(itemDto.getIdProduct()))")
    Item itemDtoToItem(ItemDto itemDto);
    List<Item> itemDtoListToItemList(List<ItemDto> itemDtoList);
    List<ItemDto> itemListToItemDtoList(List<Item> itemList);
}
