package com.superapp.bill;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBillMapper {
    @Mapping(target = "personDto", expression = "java(com.superapp.person.IPersonMapper.INSTANCE.personToPersonDto(bill.getPerson()))")
    BillDto billToBillDto(Bill bill);
    @Mapping(target = "person", expression = "java(com.superapp.person.IPersonMapper.INSTANCE.personDtoToPerson(billDto.getPersonDto()))")
    Bill billDtoToBill(BillDto billDto);
    List<BillDto> billListToBillDtoList(List<Bill> billList);
    List<Bill> billDtoListToBill(List<BillDto> billDtoList);
}
