package com.zakharov.testtask.exception;

public class PersonNotFoundException extends RuntimeException {
    private int id;

    public PersonNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Person with id:" + id + " not found";
    }
}
