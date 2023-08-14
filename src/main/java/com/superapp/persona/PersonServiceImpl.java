package com.superapp.persona;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
public class PersonServiceImpl implements IPersonService {
    @Autowired
    IPersonJpaDao personJpaDao;

    @Autowired
    IPersonMapper personMapper;


    @Override
    public List<Person> findall() {
        return personJpaDao.findAll();
    }

    @Override
    public Person createPerson(PersonDto personDto) {
        return personJpaDao.save(personMapper.personDtoToPerson(personDto));
    }

    @Override
    public Person findById(Integer id) {
        return personJpaDao.findById(id).get();
    }

    @Override
    public Person updatePerson(PersonDto personDto) {
//        Person personToEdit = personJpaDao.findById(personDto.getId()).get();
        return null;
    }

    @Override
    public Person deletePerson(Integer id) {
        return null;
    }
}
