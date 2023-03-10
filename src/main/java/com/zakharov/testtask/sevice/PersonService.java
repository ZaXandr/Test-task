package com.zakharov.testtask.sevice;

import com.zakharov.testtask.dto.PersonDto;
import com.zakharov.testtask.exception.PersonNotFoundException;
import com.zakharov.testtask.mapper.PersonMapper;
import com.zakharov.testtask.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDto getPersonById(int id) {
        return personRepository.findById(id)
                .map(person -> {
                    PersonDto personDto = personMapper.toDto(person);
                    personDto.setAge(getAge(person.getDayOfBirth()));
                    return personDto;
                })
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public int getAge(LocalDate date){
        return Period.between(date,LocalDate.now()).getYears();
    }

}
