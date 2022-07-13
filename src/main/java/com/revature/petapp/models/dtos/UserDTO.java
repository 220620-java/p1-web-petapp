package com.revature.petapp.models.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.revature.petapp.models.Pet;
import com.revature.petapp.models.User;

/**
 * Data Transfer Object to prepare a User object to be sent in an HTTP response. 
 * Looks like a User but without the password.
 * @author SierraNicholes
 *
 */
public class UserDTO {
	private int id;
	private String username;
	private List<Pet> pets;
	
	public UserDTO() {
		super();
		this.id = 0;
		this.username = "";
		this.pets = new ArrayList<>();
	}
	
	public UserDTO(int id, String username, List<Pet> pets) {
		super();
		setId(id);
		setUsername(username);
		setPets(pets);
	}
	
	public UserDTO(User user) {
		super();
		setId(user.getId());
		setUsername(user.getUsername());
		setPets(user.getPets());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pets, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return id == other.id && Objects.equals(pets, other.pets) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pets=" + pets + "]";
	}
}
