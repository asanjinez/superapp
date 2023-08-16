package com.superapp.person;

import java.util.List;

public interface IPersonService {
    public List<Person> findAll();
    public Person createPerson(PersonDto personDto);
    public Person findById(Integer id);
    public Person updatePerson(PersonDto personDto);
    public void deletePerson(Integer id);

}
