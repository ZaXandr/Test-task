package com.zakharov.testtask.controller;

import com.zakharov.testtask.entity.Person;
import com.zakharov.testtask.sevice.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id){
        return personService.getPersonById(id);
    }

    @PostMapping()
    public Person savePerson(@RequestBody Person person){
        return personService.addPerson(person);
    }
}
