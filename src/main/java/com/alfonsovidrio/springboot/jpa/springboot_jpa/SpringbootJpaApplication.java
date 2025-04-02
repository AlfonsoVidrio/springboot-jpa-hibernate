package com.alfonsovidrio.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.alfonsovidrio.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.alfonsovidrio.springboot.jpa.springboot_jpa.entities.Person;
import com.alfonsovidrio.springboot.jpa.springboot_jpa.repopsitories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		betweenQueries();
		// concatUpperLowerQueries();
		// distinctQueries();
		// personalizeQuery2();
		// personalizeQuery();
		// delete2();
		// delete();
		// update();
		// create();
		// list();
		// findOne();
	}



	@Transactional(readOnly = true)
	public void betweenQueries() {
		System.out.println("================ Queries with person id between 2 and 5 ================");
		// List<Person> persons = repository.findAllBetweenId(2,5);
		List<Person> persons = repository.findByIdBetweenOrderByIdAsc(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("================ Queries with person name between J and Q ================");
		// List<Person> personsName = repository.findAllBetweenName("J", "Q");
		List<Person> personsName = repository.findByNameBetweenOrderByIdDesc("J", "Q");
		personsName.forEach(System.out::println);

		System.out.println("================ Queries with person order by name ================");
		// List<Person> personsOrder = repository.getAllOrdered();
		List<Person> personsOrder = repository.findAllByOrderByNameDesc();
		personsOrder.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void concatUpperLowerQueries() {
		System.out.println("================ Queries with person name ================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);
	
		System.out.println("================ Queries with person name upper ================");
		List<String> namesUpper = repository.findAllFullNameUpper();
		namesUpper.forEach(System.out::println);
		System.out.println("================ Queries with person name lower ================");
		List<String> namesLower = repository.findAllFullNameLower();
		namesLower.forEach(System.out::println);
		System.out.println("================ Queries with person name upper and lower ================");
		List<Object[]> personsData = repository.findAllUpperLowerDataList();
		personsData.forEach(p -> System.out.println("Name: " + p[1] + ", Lastname: " + p[2] + ", Programming Language: " + p[3]));
	}

	@Transactional(readOnly = true)
	public void distinctQueries() {
		System.out.println("================ Queries with person name ================");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("================ Queries with distinct person name ================");
		List<String> namesDistinct = repository.findAllNamesDistinct();
		namesDistinct.forEach(System.out::println);

		System.out.println("================ Queries with distinct programming languages ================");
		List<String> programmingLanguages = repository.findAllProgrammingLanguages();
		programmingLanguages.forEach(System.out::println);

		System.out.println("================ Queries with distinct programming languages count ================");
		Long count = repository.countDistinctProgrammingLanguages();
		System.out.println("Count: " + count);

	}

	@Transactional(readOnly = true)
	public void personalizeQuery2() {
		System.out.println("================ Query by person object and programming language ================");
		List<Object[]> personsData = repository.findAllMixPersonDataList();
		personsData.forEach(p ->System.out.println("Programming Language: " + p[1] + ", person: " + p[0]));

		System.out.println("================ Query by personalized object ================");
		List<Person> persons = repository.findAllPersonalizedObjectPerson();
		persons.forEach(System.out::println);

		System.out.println("================ Query by personalized object DTO ================");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizeQuery() {
		Scanner sn = new Scanner(System.in);
		// System.out.println("================ Query the id by name ================");
		// System.out.print("Enter name: ");
		// String name2 = sn.next();
		// Long id2 = repository.getIdByName(name2);
		// System.out.println("Id: " + id2);

		System.out.println("================ Query the name by id ================");
		System.out.print("Enter id: ");
		Long id = sn.nextLong();
		String name = repository.getNameById(id);
		System.out.println("Name: " + name);

		System.out.println("================ Query the full name by id ================");
		String fullName = repository.getFullNameById(id);
		System.out.println("Full name: " + fullName);

		System.out.println("================ Query person data by id ================");
		Optional<Object> personData = repository.findDataPersonById(id);
		if (personData.isPresent()) {
			Object[] person = (Object[]) personData.orElseThrow();
			System.out.println("Id: " + person[0] + " Name: " + person[1] + " Lastname: " + person[2]
					+ " Programming Language: " + person[3]);
		} else {
			System.out.println("Person with ID " + id + " not found");
		}
		System.out.println("================ Query all person data ================");
		List<Object[]> personsData = repository.findDataPersonList();
		personsData.forEach(p -> System.out.println(p[0] + " " + p[1] + " " + p[2] + " " + p[3]));



		
		sn.close();
	}

	@Transactional
	public void delete2() {
		Scanner sn = new Scanner(System.in);
		System.out.print("Enter id to delete: ");
		Long id = sn.nextLong();

		repository.findById(id).ifPresentOrElse(repository::delete,
				() -> System.out.println("Person with ID " + id + " not found"));

		repository.findAll().forEach(System.out::println);

		sn.close();
	}

	@Transactional
	public void delete() {
		Scanner sn = new Scanner(System.in);
		System.out.print("Enter id to delete: ");
		Long id = sn.nextLong();

		repository.deleteById(id);
		repository.findAll().forEach(System.out::println);

		sn.close();
	}

	@Transactional
	public void update() {
		Scanner sn = new Scanner(System.in);
		System.out.print("Enter id to update: ");
		Long id = sn.nextLong();

		Optional<Person> personOptional = repository.findById(id);

		personOptional.ifPresentOrElse(person -> {
			System.out.println(person);
			System.out.print("Enter new programming language: ");
			String programmingLanguage = sn.next();
			person.setProgrammingLanguage(programmingLanguage);
			Person updatedPerson = repository.save(person);
			System.out.println(updatedPerson);
		}, () -> System.out.println("Person with ID " + id + " not found"));

		sn.close();
	}

	@Transactional
	public void create() {
		Scanner sn = new Scanner(System.in);
		System.out.print("Enter name: ");
		String name = sn.next();
		System.out.print("Enter lastname: ");
		String lastname = sn.next();
		System.out.print("Enter programming language: ");
		String programmingLanguage = sn.next();
		sn.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = null;
		// Optional<Person> personOptional = repository.findById(2L);
		// if(personOptional.isPresent()) {
		// person = personOptional.get();
		// }
		// System.out.println(person);

		// repository.findById(1L).ifPresent(System.out::println);
		// repository.findOne(2L).ifPresent(System.out::println);
		// repository.findOneName("Maria").ifPresent(System.out::println);

		repository.findOneLikeName("ria").ifPresent(System.out::println);
		repository.findByNameContaining("fa").ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> persons = (List<Person>)
		// repository.findByProgrammingLanguage("Java");

		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");
		persons.stream().forEach(System.out::println);

		List<Object[]> personsData = repository.findDataPerson();
		personsData.stream().forEach(p -> System.out.println(p[0] + " is expert in " + p[1]));
	}

}
