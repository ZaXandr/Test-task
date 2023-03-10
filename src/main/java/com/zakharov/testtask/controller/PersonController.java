package com.zakharov.testtask.controller;

import com.zakharov.testtask.dto.PersonDto;
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
    public PersonDto getPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

//    @PostMapping("/save")
//    public Person savePerson(@RequestBody Person person){
//        return personService.save(person);
//    }

}
