package com.superapp.persona;

import java.util.List;

public interface IPersonService {
    public List<Person> findall();
    public Person createPerson(PersonDto personDto);
    public Person findById(Integer id);
    public Person updatePerson(PersonDto personDto);
    public Person deletePerson(Integer id);

}
