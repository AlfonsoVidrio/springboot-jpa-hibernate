package com.alfonsovidrio.springboot.jpa.springboot_jpa.repopsitories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alfonsovidrio.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.alfonsovidrio.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
    // @Query("SELECT p FROM Person p WHERE p.id NOT IN ?1")
    @Query("SELECT p FROM Person p WHERE p.id IN ?1")
    List<Person> getPersonsByIds(List<Long> ids);

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name)=(SELECT MIN(LENGTH(p.name)) FROM Person p)")
    List<Object[]> getShorterName();

    @Query("SELECT p FROM Person p WHERE p.id = (SELECT MAX(p.id) FROM Person p)")
    Optional<Person> getLastRegistration();
    
    @Query("SELECT MIN(p.id), MAX(p.id), SUM(p.id), AVG(LENGTH(p.name)), COUNT(p.id) FROM Person p")
    Object getResumeAggregationFunction();

    @Query("SELECT MAX(LENGTH(p.name)) FROM Person p")
    Integer getMaxLengthName();

    @Query("SELECT MIN(LENGTH(p.name)) FROM Person p")
    Integer getMinLengthName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p")
    List<Object[]> getPersonNameLen();

    @Query("SELECT COUNT(p) FROM Person p")
    Long getCountPerson();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long getMinId();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long getMaxId();

    List<Person> findAllByOrderByNameDesc();

    @Query("SELECT p FROM Person p ORDER BY p.name, p.lastname DESC")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByIdAsc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByIdDesc(String c1, String c2);

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2 ORDER BY p.name ASC")
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN ?1 and ?2 ORDER BY p.name, p.lastname DESC")
    List<Person> findAllBetweenId(int id1, int id2);

    @Query("SELECT p.id, UPPER(p.name), LOWER(p.lastname), UPPER(p.programmingLanguage) FROM Person p")
    List<Object[]> findAllUpperLowerDataList();

    @Query("SELECT UPPER(p.name || ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameUpper();

    @Query("SELECT LOWER(CONCAT(p.name, ' ', p.lastname)) FROM Person p")
    List<String> findAllFullNameLower();

    // @Query("SELECT CONCAT(p.name, ' ', p.lastname) FROM Person p")
    @Query("SELECT p.name || ' ' || p.lastname FROM Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage)) FROM Person p")
    Long countDistinctProgrammingLanguages();

    @Query("SELECT DISTINCT(p.programmingLanguage) FROM Person p")
    List<String> findAllProgrammingLanguages();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT new com.alfonsovidrio.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) FROM Person p")
    List<PersonDto> findAllPersonDto();

    @Query("SELECT new Person(p.name, p.lastname) FROM Person p")
    List<Person> findAllPersonalizedObjectPerson();
    
    @Query("SELECT p.name FROM Person p WHERE p.id=?1")
    String getNameById(Long id);

    @Query("SELECT p.id FROM Person p WHERE p.name=?1")
    Long getIdByName(String name);

    @Query("SELECT CONCAT(p.name, ' ', p.lastname) as fullname FROM Person p WHERE p.id=?1")
    String getFullNameById(Long id);
    
    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPersonDataList();
    

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1") 
    List<Person> findByProgrammingLanguage(String programmingLanguage);
    
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);
    
    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p")
    List<Object[]> findDataPersonList();

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p WHERE p.id=?1")
    Optional<Object> findDataPersonById(Long id);

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
