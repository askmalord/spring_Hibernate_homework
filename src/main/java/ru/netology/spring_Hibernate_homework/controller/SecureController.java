package ru.netology.spring_Hibernate_homework.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.spring_Hibernate_homework.entities.Person;
import ru.netology.spring_Hibernate_homework.repository.MyCRUDRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class SecureController{
    private MyCRUDRepository repository;

    public SecureController(MyCRUDRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/by-city")
    @Secured("ROLE_READ")
    public List<Person> getPersonByCity(@RequestParam("city") String city) {
        List<Person> result = repository.findByCityOfLiving(city);
        return result;
    }

    @GetMapping("/by-age")
    @RolesAllowed("ROLE_WRITE")
    public List<Person> getPersonByAge(@RequestParam("age") int age) {
        List<Person> result = repository.findByAgeLessThan(age);
        return result;
    }

    @GetMapping("/by-name-and-surname")
    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    public Optional<List<Person>> getPersonByNameAndSurname(@RequestParam("name") String name,
                                                            @RequestParam("surname") String surname) {
        Optional<List<Person>> result = repository.findByNameSurname(name, surname);
        return result;
    }

    @GetMapping("/by-name")
    @PreAuthorize("#username == authentication.principal.username")
    public List<Person> getPersonByName(@RequestParam("username") String username) {
        List<Person> result = repository.findByName(username);
        return result;
    }
}
