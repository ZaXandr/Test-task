package com.zakharov.testtask.sevice;

import com.zakharov.testtask.PersonRepository;
import com.zakharov.testtask.entity.Person;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
    final private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(int id){
        return personRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }
}
