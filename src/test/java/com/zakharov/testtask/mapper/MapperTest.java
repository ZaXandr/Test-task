package com.zakharov.testtask.mapper;

import com.zakharov.testtask.dto.PersonDto;
import com.zakharov.testtask.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Autowired
    private PersonMapper mapper;

    @Test
    public void givenPersonMapper_whenMapperMapToDto_ShouldReturnDto(){

        Person person = new Person(1,"Jon","Snow", LocalDate.of(2000,1,1));
        PersonDto get = mapper.toDto(person);

        assertThat(get.getId()).isEqualTo(person.getId());
        assertThat(get.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(get.getLastName()).isEqualTo(person.getLastName());

    }

}
