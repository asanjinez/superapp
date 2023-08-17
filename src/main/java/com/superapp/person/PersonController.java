package com.superapp.person;

import com.superapp.cart.ICartService;
import com.superapp.exception.ExistingNameException;
import com.superapp.exception.NoPersonFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IPersonMapper personMapper;

    @PostMapping
    public ResponseEntity createPerson(@RequestBody PersonDto personDto) {
        try {
            PersonDto personCreated = personMapper.personToPersonDto(personService.createPerson(personDto));
            cartService.createCart(personCreated);
            return new ResponseEntity<PersonDto>(personCreated, HttpStatus.CREATED);

        } catch (ExistingNameException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity findAll(){
        try {
            List<PersonDto> persons = personMapper.personListToPersonDtoList(personService.findAll());
            return new ResponseEntity<List<PersonDto>>(persons,HttpStatus.OK);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        try {
            PersonDto personFound = personMapper.personToPersonDto(personService.findById(id));
            return new ResponseEntity<PersonDto>(personFound,HttpStatus.FOUND);

        } catch (NoPersonFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody PersonDto personDto){
        try {
            PersonDto personUpdated = personMapper.personToPersonDto(personService.updatePerson(personDto));
            return new ResponseEntity<PersonDto>(personUpdated,HttpStatus.OK);

        } catch (NoPersonFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestBody Integer id){
        try {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NoPersonFoundException e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("Unknown error :c", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
