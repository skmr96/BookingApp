
package com.irctc.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Seat;
import com.irctc.booking.model.SeatStatus;
import com.irctc.booking.model.Train;
import com.irctc.booking.repository.CoachRepository;
import com.irctc.booking.repository.SeatRepository;
import com.irctc.booking.repository.TrainRepository;

@Service
public class TrainService {
	
	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private SeatRepository seatRepo;
	
	@Autowired
	private CoachRepository coachRepo;

	public Train getTrain(Long trainId) {
		Optional<Train> train =  trainRepo.findById(trainId);
		return train.isPresent() ? train.get() : null;
	}
	
	public Train addTrain(Train train) {
		return trainRepo.save(train);
	}
	
	public List<Train> getAllTrains() {
		return trainRepo.findAll();
	}

	public List<Seat> getAllSeats(Train train, SeatStatus status) 
	{
		List<Coach> coaches = coachRepo.findAllByTrain(train);
		List<Long> coachIds = coaches.stream().map(Coach::getCoachId).collect(Collectors.toList());
		if(status != null) {
			return seatRepo.findByCoachIdInAndStatus(coachIds, status);
		}
		return seatRepo.findByCoachIdIn(coachIds);
	}

}
