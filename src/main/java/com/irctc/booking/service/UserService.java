package com.irctc.booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.booking.model.User;
import com.irctc.booking.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRepo;

	public User getUser(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		return user.isPresent() ? user.get() : null;
	}
	
	public User addUser(User user) {
		return userRepo.save(user);
	}
	

}
