package com.zakharov.testtask.service;

import com.zakharov.testtask.dto.PersonDto;
import com.zakharov.testtask.entity.Person;
import com.zakharov.testtask.exception.PersonNotFoundException;
import com.zakharov.testtask.repository.PersonRepository;
import com.zakharov.testtask.sevice.PersonService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceUnitTest {

    @MockBean
    private PersonRepository repository;

    @Autowired
    private PersonService service;

    @Test
    public void givenPersonId_whenPersonGetById_thenReturnPerson() {

        int id = 1;
        LocalDate date = LocalDate.of(2000, 1, 1);
        PersonDto testDto = new PersonDto();

        when(repository.findById(id)).thenReturn(Optional.of(
                new Person(1, "Jon", "Snow", date)));

        PersonDto found = service.getPersonById(id);

        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getFirstName()).isEqualTo("Jon");
        assertThat(found.getLastName()).isEqualTo("Snow");
        assertThat(found.getAge()).isEqualTo(Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    public void givenPersonId_whenPersonGetById_thenThrowException() {
        int id = 10;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(PersonNotFoundException.class, () -> service.getPersonById(id));
        assertThat(exception.getMessage()).isEqualTo("Person with id:" + id + " not found");
    }

    @Test
    public void test_calculateAge(){

        LocalDate date = LocalDate.of(2001,1,1);

        int calcAge = service.getAge(date);
        assertThat(calcAge).isEqualTo(Period.between(date,LocalDate.now()).getYears());
    }
}
