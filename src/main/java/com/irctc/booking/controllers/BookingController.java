package com.irctc.booking.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.booking.model.Booking;
import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Seat;
import com.irctc.booking.model.SeatStatus;
import com.irctc.booking.model.Train;
import com.irctc.booking.model.User;
import com.irctc.booking.service.BookingService;
import com.irctc.booking.service.CoachService;
import com.irctc.booking.service.SeatService;
import com.irctc.booking.service.TrainService;
import com.irctc.booking.service.UserService;
import com.irctc.booking.utils.CoachUtils;

@RestController
public class BookingController {
	
	private static final String SEATS_BOOKED = "Given Seats are Booked. Please choose another Seats";
	
	@Autowired
	private TrainService trainService;
	
	@Autowired
	private CoachService coachService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("api/v1/trains/{trainId}/coaches/{coachId}/seats/{seatId}/book")
	public Booking bookSeats(@PathVariable Long trainId, @PathVariable Long coachId, 
			@PathVariable Long seatId, @RequestParam(required=true) Long userId) throws ValidationException
	{
		return bookMultipleSeats(trainId, coachId, Arrays.asList(seatId), userId);
	}
	
	@PostMapping("api/v1/trains/{trainId}/coaches/{coachId}/bookSeats")
	public Booking bookMultipleSeats(@PathVariable Long trainId, @PathVariable Long coachId, @RequestBody List<Long> seatIds, @RequestParam(required=true)Long userId) throws ValidationException // Seat Nos 
	{
		User user = userService.getUser(userId);
		Train train = trainService.getTrain(trainId);
		Coach coach = coachService.getCoach(coachId);
		CoachUtils.doesCoachesExistInTheTrain(Arrays.asList(coach), train);
		List<Seat> seats = seatService.getSeats(seatIds);
		doesSeatsRemainsUnBooked(seats);
		Booking booking = bookingService.bookSeats(train,coach,seatIds,user);
		List<Integer> seatNos = seats.stream().map(Seat::getSeatNo).collect(Collectors.toList());
		booking.setSeatNos(seatNos);
		return booking;
	}
	
	private void doesSeatsRemainsUnBooked(List<Seat> seats) throws ValidationException
	{
		Long bookedSeats = seats.stream().filter(seat -> SeatStatus.BOOKED.equals(seat.getStatus())).count();
		if(bookedSeats >= 1 ) {
			throw new ValidationException(SEATS_BOOKED);
		}
	}

}
