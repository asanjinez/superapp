package com.superapp.bill_service.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IItemMapper {
    IItemMapper INSTANCE = Mappers.getMapper(IItemMapper.class);

    ItemDto itemToItemDto(Item item);
    Item itemDtoToItem(ItemDto itemDto);
    List<Item> itemDtoListToItemList(List<ItemDto> itemDtoList);
    List<ItemDto> itemListToItemDtoList(List<Item> itemList);
}
