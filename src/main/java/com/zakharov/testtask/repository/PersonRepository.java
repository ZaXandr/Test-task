package com.zakharov.testtask.repository;

import com.zakharov.testtask.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}

