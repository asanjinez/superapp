package com.superapp.bill_service.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IItemMapper {
    @Mapping(target = "bill", expression = "java(item.getBill().getId())")
    ItemDto itemToItemDto(Item item);
    @Mapping(target = "bill", expression = "java(com.superapp.product_service.product.IProductService.INSTANCE.findById(itemDto.getIdProduct()))")
    Item itemDtoToItem(ItemDto itemDto);
    List<Item> itemDtoListToItemList(List<ItemDto> itemDtoList);
    List<ItemDto> itemListToItemDtoList(List<Item> itemList);
}
