package com.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.entities.Actor;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ActorRepository dao;
	
	@Override
	@Transactional
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
//		dao.findByActorIdGreaterThan(200).forEach(System.out::println);
//		dao.dameNuevosJPQL(200).forEach(System.out::println);
//		dao.dameNuevosSQL(200).forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.lessThanOrEqualTo(root.get("actorId"), 10)).forEach(System.out::println);
//		var item = dao.findById(1);
//		if(item.isEmpty())
//			System.err.println("No encontrado");
//		else {
//			var actor = item.get();
//			System.out.println(actor);
//			actor.getFilmActors().forEach(f-> System.out.println(f.getFilm().getTitle()));
//			dao.findById(2).get().getFilmActors().forEach(f-> System.out.println(f.getFilm().getTitle()));
//		}
//		var actor = new Actor(0, null, "kkkk");
//		if(actor.isInvalid())
//			System.err.println(actor.getErrorsMessage());
//		else 
//			System.out.println("Es validor");
//		dao.save(actor);
//		dao.readBy().forEach(f-> System.out.println(f.getId() + " " + f.getNombre()));
//		dao.findAll().forEach(f -> System.out.println(ActorDTO.from(f)));
//		System.out.println(ActorDTO.from(new ActorDTO(111, "de", "fuera")));
		dao.findAllBy(ActorDTO.class).forEach(System.out::println);
//		dao.findAllBy(ActorShort.class).forEach(f-> System.out.println(f.getId() + " " + f.getNombre()));
	}

}
