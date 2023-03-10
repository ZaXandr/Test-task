package com.zakharov.testtask.repository;

import com.zakharov.testtask.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("Test")
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
    private PersonRepository repository;


    @Before
    public void setUp(){
        repository.saveAllAndFlush(
                List.of(
                new Person("Jon","Snow",LocalDate.of(2001,1,1)),
                new Person("Test","Test",LocalDate.of(2010,1,1))));
    }

    @Test
    public void whenPersonFindById_thenShouldReturnCorrectPerson() {
        Person person = new Person(1,"Jon", "Snow", LocalDate.of(2001, 1, 1));

        Person found = repository.findById(person.getId()).get();

        assertThat(person.getId()).isEqualTo(found.getId());
        assertThat(person.getFirstName()).isEqualTo(found.getFirstName());
        assertThat(person.getLastName()).isEqualTo(found.getLastName());
        assertThat(person.getDayOfBirth()).isEqualTo(found.getDayOfBirth());
    }

    @Test
    public void whenPersonFindById_thenShouldNotFindPerson(){
        assertThat(repository.findById(100)).isEmpty();
    }


}
