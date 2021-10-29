package ru.netology.spring_Hibernate_homework.repository;

import org.springframework.stereotype.Repository;
import ru.netology.spring_Hibernate_homework.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersonsByCity(String city) {
        Query query = entityManager
                .createQuery("Select p FROM Person p WHERE p.cityOfLiving = :city", Person.class);
        query.setParameter("city", city);
        List<Person> resultPersons = query.getResultList();
        return resultPersons;
    }
}
