package com.foodchoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Instruction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String instruction;
	
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

}
