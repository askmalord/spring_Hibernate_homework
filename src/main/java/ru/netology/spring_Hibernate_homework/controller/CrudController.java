package ru.netology.spring_Hibernate_homework.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.spring_Hibernate_homework.entities.Person;
import ru.netology.spring_Hibernate_homework.repository.MyCRUDRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class CrudController {

    private MyCRUDRepository repository;

    public CrudController(MyCRUDRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/by-city")
    public List<Person> getPersonByCity(@RequestParam("city") String city) {
        List<Person> result = repository.findByCityOfLiving(city);
        return result;
    }

    @GetMapping("/by-age")
    public List<Person> getPersonByAge(@RequestParam("age") int age) {
        List<Person> result = repository.findByAgeLessThan(age);
        return result;
    }

    @GetMapping("/by-name-and-surname")
    public Optional<List<Person>> getPersonByNameAndSurname(@RequestParam("name") String name,
                                                            @RequestParam("surname") String surname) {
        Optional<List<Person>> result = repository.findByNameSurname(name, surname);
        return result;
    }
}
