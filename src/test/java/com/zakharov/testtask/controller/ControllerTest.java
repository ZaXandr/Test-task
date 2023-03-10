package com.zakharov.testtask.controller;

import com.zakharov.testtask.entity.Person;
import com.zakharov.testtask.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Period;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository personRepository;


    @Test
    public void whenPersonGetById_ThenStatusCode200() throws Exception {
        int id = 1;
        LocalDate date = LocalDate.of(2000, 01, 01);

        Person testPerson = new Person();
        testPerson.setId(id);
        testPerson.setFirstName("Jon");
        testPerson.setLastName("Snow");
        testPerson.setDayOfBirth(date);

        personRepository.save(testPerson);

        mvc.perform(get("/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("firstName").value("Jon"))
                .andExpect(jsonPath("lastName").value("Snow"))
                .andExpect(jsonPath("age").value(Period.between(date, LocalDate.now()).getYears()));
    }

    @Test
    public void whenPersonGetById_ThenStatusCode404() throws Exception {
        int id = 1011;

        mvc.perform(get("/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Person with id:" + id + " not found"));
    }


}
