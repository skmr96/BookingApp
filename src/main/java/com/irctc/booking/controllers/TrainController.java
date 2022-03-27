package com.irctc.booking.controllers;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Role;
import com.irctc.booking.model.Seat;
import com.irctc.booking.model.SeatStatus;
import com.irctc.booking.model.Train;
import com.irctc.booking.model.User;
import com.irctc.booking.service.CoachService;
import com.irctc.booking.service.TrainService;
import com.irctc.booking.service.UserService;
import com.irctc.booking.utils.CoachUtils;

@Configuration
@RestController
public class TrainController {
	
	private static final String ADMINS_ONLY_ALLOWED = "Only Admins are allowed to perform this operation";
	
	@Autowired
	TrainService trainService;
	
	@Autowired
	CoachService coachService;
	
	@Autowired
	UserService userService;

	@GetMapping("/api/v1/trains/{trainId}/seats")
	public List<Seat> getAllSeatsInTrain(@PathVariable Long trainId, @RequestParam(required = false) SeatStatus status, @RequestParam(required=true) Long userId) throws ValidationException 
	{
		isAdmin(userId);
		Train train = trainService.getTrain(trainId);
		return trainService.getAllSeats(train, status);
	}

	@PostMapping("api/v1/trains/{trainId}/addCoaches")
	public List<Coach> addCoaches(@PathVariable Long trainId, @RequestBody List<Coach> coaches, @RequestParam(required=true) Long userId) throws ValidationException
	{
		isAdmin(userId);
		Train train = trainService.getTrain(trainId);
		coaches.forEach(coach -> coach.setTrain(train));
		return coachService.addCoaches(coaches);
	}
	
	@PatchMapping("api/v1/trains/{trainId}/updateCoaches")
	public List<Coach> updateCoaches(@PathVariable Long trainId, @RequestBody List<Coach> coaches, @RequestParam(required=true) Long userId) throws ValidationException
	{
		isAdmin(userId);
		Train train = trainService.getTrain(trainId);
		CoachUtils.doesCoachesExistInTheTrain(coaches,train);
		return coachService.updateCoaches(coaches);
	}

	@PostMapping("api/v1/trains/{trainId}/removeCoaches")
	public void removeCoaches(@PathVariable Long trainId, @RequestBody List<Long> coachIds, @RequestParam(required=true) Long userId) throws ValidationException
	{
		isAdmin(userId);
		Train train = trainService.getTrain(trainId);
		List<Coach> coaches = coachService.getCoaches(coachIds,train);
		CoachUtils.doesCoachesExistInTheTrain(coaches,train);
		coachService.deleteCoaches(coaches);
	}

	@GetMapping("/api/v1/coaches/{coachId}/seats")
	public List<Seat> getSeats(@PathVariable Long coachId, @RequestParam(required = false) SeatStatus status)
	{
		return coachService.getSeats(coachId,status);
	}
	
	@GetMapping("/api/v1/trains")
	public List<Train> getAllTrains() 
	{
		return trainService.getAllTrains();
	}
	
	private void isAdmin(Long userId) throws ValidationException
	{
		User user = userService.getUser(userId);
		if(user.getRole() != Role.ADMIN) {
			throw new ValidationException(ADMINS_ONLY_ALLOWED);
		}
	}
	
}
