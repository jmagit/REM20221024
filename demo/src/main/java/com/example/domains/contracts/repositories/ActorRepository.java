package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domains.enties.dtos.ActorShort;
import com.example.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {
	List<Actor> findTop10ByFirstNameStartingWithOrderByLastNameDesc(String prefijo);
	List<Actor> findTop10ByFirstNameStartingWith(String prefijo, Sort orden);

	List<Actor> findByActorIdGreaterThan(int id);
	@Query("SELECT a FROM Actor a WHERE a.actorId = ?1")
	List<Actor> dameNuevosJPQL(int id);
	@Query(value = "SELECT * FROM actor a WHERE a.actor_id = :id", nativeQuery = true)
	List<Actor> dameNuevosSQL(@Param("id") int id);
	
	List<ActorShort> readBy();
	
	<T> Iterable<T> findAllBy(Class<T> tipo);
	<T> Iterable<T> findAllBy(Sort sort, Class<T> tipo);
	<T> Page<T> findAllBy(Pageable pageable, Class<T> tipo);
}
