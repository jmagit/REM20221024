package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.enties.Actor;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ActorRepository dao;
	
	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Hola mundo");
//		var actor = new Actor(0, "PEPITO", "Grillo");
//		dao.save(actor);
//		var item = dao.findById(211);
//		if(item.isEmpty())
//			System.err.println("No encontrado");
//		else {
//			var actor = item.get();
//			actor.setLastName(actor.getLastName().toUpperCase());
//			dao.save(actor);
//		}
//		dao.deleteById(210);
//		dao.findAll().forEach(System.out::println);
//		dao.findTop10ByFirstNameStartingWithOrderByLastNameDesc("P").forEach(System.out::println);
//		dao.findTop10ByFirstNameStartingWith("P", Sort.by("firstName").descending()).forEach(System.out::println);
		dao.findByActorIdGreaterThan(200).forEach(System.out::println);
		dao.dameNuevosJPQL(200).forEach(System.out::println);
		dao.dameNuevosSQL(200).forEach(System.out::println);
		dao.findAll((root, query, builder) -> builder.lessThanOrEqualTo(root.get("actorId"), 10)).forEach(System.out::println);
	}

}
