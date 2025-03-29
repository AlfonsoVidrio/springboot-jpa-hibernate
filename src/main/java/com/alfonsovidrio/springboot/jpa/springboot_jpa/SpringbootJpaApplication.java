package com.alfonsovidrio.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		delete2();
		// delete();
		// update();
		// create();
		// list();
		// findOne();
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
