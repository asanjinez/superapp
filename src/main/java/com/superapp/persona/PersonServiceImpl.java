package com.superapp.persona;

import com.superapp.exception.ExistingNameException;
import com.superapp.exception.NoPersonFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class PersonServiceImpl implements IPersonService {
    @Autowired
    IPersonJpaDao personJpaDao;

    @Autowired
    IPersonMapper personMapper;


    @Override
    public List<Person> findAll() {
        return personJpaDao.findAll();
    }

    @Override
    public Person createPerson(PersonDto personDto) {
        Person personToCreate = personJpaDao.findById(personDto.getId()).get();
        if(personToCreate != null)
            throw new ExistingNameException("Alredy exists a person with id " + personDto.getId());
        return personJpaDao.save(personMapper.personDtoToPerson(personDto));
    }

    @Override
    public Person findById(Integer id) {
        return this.personExists(id);
    }

    @Override
    public Person updatePerson(PersonDto personDto) {
        Person personToEdit = this.personExists(personDto.getId());
        personToEdit.setUsername(personDto.getUsername());
        personToEdit.setEmail(personDto.getEmail());
        personToEdit.setPassword(personDto.getPassword());
        personToEdit.setName(personDto.getName());
        personToEdit.setLastname(personDto.getLastname());
        personToEdit.setType(personMapper.stringToType(personDto.getType()));

        return personJpaDao.save(personToEdit);
    }

    @Override
    public void deletePerson(Integer id) {
        Person personToDelete = this.personExists(id);
            personJpaDao.delete(personToDelete);
    }

    private Person personExists(Integer id){
        Optional<Person> person = personJpaDao.findById(id);
        if (!person.isPresent())
            throw new NoPersonFoundException("This person doesn't exist");

        return person.get();
    }
}
