package ru.netology.spring_Hibernate_homework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.spring_Hibernate_homework.entities.Person;
import ru.netology.spring_Hibernate_homework.repository.MyRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private MyRepository repository;

    public Controller(MyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons/by-city")
    public List<Person> getProduct(@RequestParam("city") String city) {
        List<Person> res = repository.getPersonsByCity(city);
        return repository.getPersonsByCity(city);
    }
}
