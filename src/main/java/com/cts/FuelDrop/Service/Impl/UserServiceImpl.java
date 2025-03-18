package com.cts.FuelDrop.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.FuelDrop.Entity.User;
import com.cts.FuelDrop.Exception.IncorrectPasswordException;
import com.cts.FuelDrop.Exception.UserAlreadyExistsException;
import com.cts.FuelDrop.Exception.UserNotFoundException;
import com.cts.FuelDrop.Repository.UserRepository;
import com.cts.FuelDrop.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {

		List<User> userList = userRepository.findAll();

		if (userList != null & userList.size() > 0) {
			return userList;
		} else
			return null;
	}

	
	
	public User addUser(User user) {
		if (user != null) {
			return userRepository.save(user);

		}
		return null;
	}


	public User registerUser(User user) throws UserAlreadyExistsException {
		Optional<User> existingUser = userRepository.findByPhoneno(user.getPhoneno());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User with phone number " + user.getPhoneno() + " already exists.");
		}
		return userRepository.save(user);
	}

	public boolean authenticate(String phoneNo, String password, String userType) {
	    User user = userRepository.findByPhoneno(phoneNo).orElse(null);
	    if (user == null || !user.getPhoneno().equals(phoneNo) || !user.getPassword().equals(password) || !user.getUsertype().equals(userType)) {
	        return false;
	    }
	    return true;
	}

	public boolean isNumberExist(String phoneno) {
		Optional<User> user = userRepository.findByPhoneno(phoneno);
		if (user.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	

}
