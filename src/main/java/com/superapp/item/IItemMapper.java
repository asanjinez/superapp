package com.superapp.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IItemMapper {
    IItemMapper INSTANCE = Mappers.getMapper(IItemMapper.class);
    @Mapping(target = "productDto", expression = "java(com.superapp.product.IProductMapper.INSTANCE.productToProductDto(item.getProduct()))")
    ItemDto itemToItemDto(Item item);
    @Mapping(target = "product", expression = "java(com.superapp.product.IProductMapper.INSTANCE.productDtoToProduct(itemDto.getProductDto()))")
    Item itemDtoToItem(ItemDto itemDto);
    List<Item> itemDtoListToItemList(List<ItemDto> itemDtoList);
    List<ItemDto> itemListToItemDtoList(List<Item> itemList);
}
