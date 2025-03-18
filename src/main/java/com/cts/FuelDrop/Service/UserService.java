package com.cts.FuelDrop.Service;

import java.util.List;

import com.cts.FuelDrop.Entity.User;
import com.cts.FuelDrop.Exception.IncorrectPasswordException;
import com.cts.FuelDrop.Exception.UserAlreadyExistsException;
import com.cts.FuelDrop.Exception.UserNotFoundException;

public interface UserService {

	public User registerUser(User user) throws UserAlreadyExistsException;

	public boolean authenticate(String phoneno, String password, String userType)
			throws UserNotFoundException, IncorrectPasswordException;


	public List<User> getAllUsers(); // for admin login

	public boolean isNumberExist(String phoneno);
	public User addUser(User user);

}
