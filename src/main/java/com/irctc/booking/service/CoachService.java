package com.irctc.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Seat;
import com.irctc.booking.model.SeatStatus;
import com.irctc.booking.model.Train;
import com.irctc.booking.repository.CoachRepository;
import com.irctc.booking.repository.SeatRepository;

@Service
public class CoachService {
	
	@Autowired
	CoachRepository coachRepo;
	
	@Autowired
	SeatRepository seatRepo;
	
	public List<Coach> addCoaches(List<Coach> coaches) 
	{
		List<Coach> addedCoaches = coachRepo.saveAll(coaches);
		List<Seat> seatsToBeAdded = new ArrayList<>();
		for(Coach coach : addedCoaches)
		{
			int noOfSeats = coach.getNoOfSeats();
			List<Seat> seats = createSeats(coach, noOfSeats);
			seatsToBeAdded.addAll(seats);
		}
		seatRepo.saveAll(seatsToBeAdded);
		return addedCoaches;
	}
	
	private List<Seat> createSeats(Coach coach, int noOfSeats)
	{
		List<Seat> seats = new ArrayList<>();
		for(int i=0; i<noOfSeats; i++) {
			Seat seat = new Seat();
			seat.setSeatNo(i+1);
			seat.setCoach(coach);
			seat.setStatus(SeatStatus.AVAILABLE);
			seats.add(seat);
		}
		return seats;
	}
	
	public Coach getCoach(Long coachId) {
		Optional<Coach> coach =  coachRepo.findById(coachId);
		return coach.isPresent() ? coach.get() : null;			
	}
	
	public List<Coach> updateCoaches(List<Coach> coaches) 
	{
		List<Coach> updatedCoaches = new ArrayList<>();
		for(Coach coach : coaches)
		{
			coachRepo.updateCoach(coach.getCoachType().getType(), coach.getCoachId());
			updatedCoaches.add(coach);
		}
		return updatedCoaches;
	}
	
	public List<Coach> getCoaches(List<Long> coachIds, Train train) 
	{
		return coachRepo.findAllByCoachIdInAndTrain(coachIds, train);
	}
	
	public void deleteCoach(Long coachId) {
		coachRepo.deleteById(coachId);
	}
	
	public void deleteCoaches(List<Coach> coaches) {
		coachRepo.deleteAll(coaches);
	}
	
	public List<Seat> getSeats(Long coachId, SeatStatus status) {
		Coach coach = getCoach(coachId);
		if(status == null) {
			return getAllSeats(coachId);
		}
		return seatRepo.findByCoachAndStatus(coach, status);
	}
	
	public List<Seat> getAllSeats(Long coachId) {
		Coach coach = getCoach(coachId);
		return seatRepo.findByCoach(coach);
	}

}
