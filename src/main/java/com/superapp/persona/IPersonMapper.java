package com.superapp.persona;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPersonMapper {
    @Mapping(target = "type", expression = "java(this.typeToString(person.getType()))")
    PersonDto personToPersonDto(Person person);
    @Mapping(target = "type", expression = "java(this.stringToType(personDto.getType()))")
    Person personDtoToPerson(PersonDto personDto);
    List<PersonDto> personListToPersonDtoList(List<Person> personList);
    List<Person> personDtoListToPerson(List<PersonDto> personDtoList);


    default String typeToString(EType type) {
        if (type == null)
            return null;
        else
            return type.name();
    }

    default EType stringToType(String type) {
        if (type == null)
            return null;
        else
            return EType.valueOf(type);
    }


}
