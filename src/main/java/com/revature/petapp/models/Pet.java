package com.revature.petapp.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Pet {
	private int id;
	private String name;
	private int age;
	//@JsonIgnoreProperties(value = {"description"})
	private Species species;
	//@JsonIgnore
	private String description;
	private Status status;
	
	public Pet() {
		super();
		this.id = 0;
		this.name = "";
		this.age = 0;
		this.species = new Species();
		this.description = "";
		this.status = new Status();
	}
	
	public Pet(String name, int age, Species species, String description) {
		super();
		this.id = 0;
		this.name = name;
		this.age = age;
		this.species = species;
		this.description = description;
		this.status = new Status();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, description, id, name, species, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return age == other.age && Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(species, other.species)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", species=" + species + ", description="
				+ description + ", status=" + status + "]";
	}
}
