package ru.netology.spring_Hibernate_homework.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.spring_Hibernate_homework.entities.Person;
import ru.netology.spring_Hibernate_homework.entities.PersonPrimaryKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyCRUDRepository extends CrudRepository<Person, PersonPrimaryKey> {
    List<Person> findByCityOfLiving(String city);

    @Query("Select p FROM Person p WHERE p.nameSurnameAge.age < :age order by p.nameSurnameAge.age")
    List<Person> findByAgeLessThan(@Param("age") int age);

    @Query("Select p FROM Person p WHERE p.nameSurnameAge.name = :name AND p.nameSurnameAge.surname = :surname")
    Optional<List<Person>> findByNameSurname(@Param("name") String name,
                                             @Param("surname") String surname);

    @Query("Select p FROM Person p WHERE p.nameSurnameAge.name = :name")
    List<Person> findByName(@Param("name") String name);
}
