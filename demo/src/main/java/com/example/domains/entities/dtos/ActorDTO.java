package com.example.domains.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@JsonInclude(content = Include.NON_EMPTY)
public class ActorDTO {
	private int actorId;
	@NotBlank
	@Size(max = 45, min=2)
	private String firstName;
	@NotBlank
	@Size(max = 45, min=2)
	@Pattern(regexp = "[A-Z]+", message = "Debe ir en máyusculas" )
	private String lastName;

	
	public static ActorDTO from(Actor target) {
		return new ActorDTO(
				target.getActorId(),
				target.getFirstName(),
				target.getLastName()
				);
	}
	public static Actor from(ActorDTO target) {
		return new Actor(
				target.getActorId(),
				target.getFirstName(),
				target.getLastName()
				);
	}
}
