package com.superapp.bill_service.bill;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBillMapper {
    IBillMapper INSTANCE = Mappers.getMapper(IBillMapper.class);

    @Mapping(target = "dtoItems", expression = "java(com.superapp.bill_service.item.IItemMapper.INSTANCE.itemListToItemDtoList(bill.getItems()))")
    public BillDto billToBillDto(Bill bill);
    @Mapping(target = "items", expression = "java(com.superapp.bill_service.item.IItemMapper.INSTANCE.itemDtoListToItemList(billDto.getDtoItems()))")
    public Bill billDtoToBill(BillDto billDto);
    public List<BillDto> billListToBillDtoList(List<Bill> billList);
    public List<Bill> billDtoListToBill(List<BillDto> billDtoList);
}
