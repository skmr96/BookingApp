package com.irctc.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.booking.model.Seat;
import com.irctc.booking.repository.SeatRepository;

@Service
public class SeatService 
{
	@Autowired
	SeatRepository seatRepo;
	
	public List<Seat> getSeats(List<Long> seatIds)
	{
		return seatRepo.findAllById(seatIds);
	}
	
	public void updateSeatsAsBooked(List<Long> seatIds)
	{
		seatRepo.updateSeatsAsBooked(seatIds);
	}

}
