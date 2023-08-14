package com.superapp.persona;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPersonMapper {
    PersonDto personToPersonDto(Person person);
    Person personDtoToPerson(PersonDto personDto);
    List<PersonDto> personListToPersonDtoList(List<Person> personList);
    List<Person> personDtoListToPerson(List<PersonDto> personDtoList);
}
