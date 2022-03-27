package com.irctc.booking.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.CoachType;
import com.irctc.booking.model.Role;
import com.irctc.booking.model.Train;
import com.irctc.booking.model.User;
import com.irctc.booking.repository.TrainRepository;
import com.irctc.booking.repository.UserRepository;

@Service
public class DataPopulatorService 
{
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private UserRepository userRepo;

	public  void populateInitialData()
	{
		trainRepo.deleteAll();
		userRepo.deleteAll();
		addAdmin();
		addUser();
		addTrain();
	}
	
	private void addAdmin()
	{
		User user = new User();
		user.setName("Satheesh");
		user.setRole(Role.ADMIN);
		userService.addUser(user);
	}
	
	private void addUser()
	{
		User user = new User();
		user.setName("Kumar");
		user.setRole(Role.USER);
		userService.addUser(user);
	}
	
	private void addTrain()
	{
		Train train = new Train();
		train.setName("Pandiyan");
		Train addedTrain = trainService.addTrain(train);
		addCoaches(addedTrain);
	}

	private void addCoaches(Train train)
	{
		Coach nonACCoach = new Coach();
		nonACCoach.setTrain(train);
		nonACCoach.setCoachType(CoachType.NON_AC);
		nonACCoach.setNoOfSeats(10);
		
		Coach acCoach = new Coach();
		acCoach.setCoachType(CoachType.AC);
		acCoach.setTrain(train);
		acCoach.setNoOfSeats(5);
		coachService.addCoaches(Arrays.asList(acCoach, nonACCoach));
	}

}
