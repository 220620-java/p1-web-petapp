package com.revature.petapp.services;

import com.revature.petapp.data.PetDAO;
import com.revature.petapp.data.PetPostgres;
import com.revature.petapp.data.StatusDAO;
import com.revature.petapp.data.StatusPostgres;
import com.revature.petapp.data.UserDAO;
import com.revature.petapp.data.UserPostgres;
import java.util.List;
import com.revature.petapp.exceptions.AlreadyAdoptedException;
import com.revature.petapp.exceptions.UsernameAlreadyExistsException;
import com.revature.petapp.models.Pet;
import com.revature.petapp.models.Status;
import com.revature.petapp.models.User;
import com.revature.petapp.utils.Logger;
import com.revature.petapp.utils.LoggingLevel;

public class UserServiceImpl implements UserService {
	private UserDAO userDao = new UserPostgres();
	private PetDAO petDao = new PetPostgres();
	private StatusDAO statusDao = new StatusPostgres();
	private Logger logger = Logger.getLogger();

	@Override
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		user = userDao.create(user);
		if (user == null) {
			// TODO this really isn't the best check for whether the username already
			// exists - the user could be null because of database connection
			// issues, etc. the DAO method should be refactored to return something
			// more descriptive in this case, probably an exception.
			throw new UsernameAlreadyExistsException();
		}
		return user;
	}

	@Override
	public User logIn(String username, String password) {
		logger.log("User service login() called", LoggingLevel.TRACE);
		User user = userDao.findByUsername(username);
		if (user != null && (password!=null && password.equals(user.getPassword()))) {
			logger.log("Successful login", LoggingLevel.INFO);
			return user;
		} else {
			logger.log("Wrong user credentials", LoggingLevel.INFO);
			return null;
		}
	}

	@Override
	public List<Pet> viewAllPets() {
		Status availableStatus = statusDao.findByName("Available");
		return petDao.findByStatus(availableStatus);
	}

	@Override
	public User adoptPet(Pet pet, User user) throws AlreadyAdoptedException {
		if (user == null || pet == null) {
			return null;
		}
		if ("Adopted".equals(pet.getStatus().getName())) {
			throw new AlreadyAdoptedException();
		}
		
		Status adoptedStatus = statusDao.findByName("Adopted");
		pet.setStatus(adoptedStatus);
		List<Pet> pets = user.getPets();
		pets.add(pet);
		user.setPets(pets);

		// TODO this should really be a transaction, but this would require some
		// refactoring of my DAO code in order to do so, so we will leave that
		// for later.
		petDao.update(pet);
		userDao.update(user);
		
		return user;
	}

}
