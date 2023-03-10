package com.zakharov.testtask.service;

import com.zakharov.testtask.dto.PersonDto;
import com.zakharov.testtask.entity.Person;
import com.zakharov.testtask.exception.PersonNotFoundException;
import com.zakharov.testtask.repository.PersonRepository;
import com.zakharov.testtask.sevice.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("Test")
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService service;


    @Before
    public void setUp(){
        Person person = new Person("Jon","Snow", LocalDate.of(2001,1,1));
        repository.save(person);
    }
    @Test
    public void whenGetById_theShouldReturnPersonDto(){


        PersonDto dto = new PersonDto();
        dto.setId(1);
        dto.setFirstName("Jon");
        dto.setLastName("Snow");
        dto.setAge(Period.between(LocalDate.now(),LocalDate.of(2001,1,1)).getYears());

        PersonDto found = service.getPersonById(1);

        assertThat(found.getId()).isSameAs(1);
        assertThat(found.getFirstName()).isEqualTo("Jon");
        assertThat(found.getLastName()).isEqualTo("Snow");
        assertThat(found.getAge()).isEqualTo(Period.between(LocalDate.of(2001,1,1),LocalDate.now()).getYears());
    }

    @Test
    public void whenGetById_theShouldThrowException(){

        Assertions.assertThrows(PersonNotFoundException.class,()-> service.getPersonById(10));
    }


}
