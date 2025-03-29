package com.alfonsovidrio.springboot.jpa.springboot_jpa.repopsitories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alfonsovidrio.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
    
    // List<Person> findByProgrammingLanguage(String programmingLanguage);
    
    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1") 
    List<Person> findByProgrammingLanguage(String programmingLanguage);
    
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);
    
    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> findDataPerson();
    
    @Query("SELECT p FROM Person p WHERE p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("SELECT p FROM Person p WHERE p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);
}
