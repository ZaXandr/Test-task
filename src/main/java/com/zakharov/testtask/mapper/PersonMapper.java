package com.zakharov.testtask.mapper;

import com.zakharov.testtask.dto.PersonDto;
import com.zakharov.testtask.entity.Person;
import org.springframework.stereotype.Component;


@Component
public class PersonMapper {

    public PersonDto toDto(Person person){
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        return personDto;
    }
}
