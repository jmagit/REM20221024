package com.example;

import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "Microservicio: Demos", version = "1.0", description = "**Demos** de Microservicios.", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"), contact = @Contact(name = "Javier Martín", url = "https://github.com/jmagit", email = "support@example.com")), externalDocs = @ExternalDocumentation(description = "Documentación del proyecto", url = "https://github.com/jmagit/curso"))
@SpringBootApplication
@EnableFeignClients("com.example.application.proxies")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public OpenApiCustomiser sortSchemasAlphabetically() {
		return openApi -> {
			var schemas = openApi.getComponents().getSchemas();
			openApi.getComponents().setSchemas(new TreeMap<>(schemas));
		};
	}

	@Bean
	@Primary
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplateLB() {
		return new RestTemplate();
	}

	@Bean
	public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
			LoadBalancerClientFactory loadBalancerClientFactory) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		name = "CATALOGO-SERVICE";
		return new RandomLoadBalancer(
				loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
	}

//	@Autowired
//	ActorRepository dao;

	@Autowired
	ActorService srv;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// System.out.println("Hola mundo");
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
//		dao.findAllBy(ActorDTO.class).forEach(System.out::println);
//		dao.findAllBy(ActorShort.class).forEach(f-> System.out.println(f.getId() + " " + f.getNombre()));
//		srv.getAll().forEach(System.out::println);
//		srv.modify(new Actor(0, null, "kkkk"));
		System.out.println("Servicio arrancado");
	}

}
