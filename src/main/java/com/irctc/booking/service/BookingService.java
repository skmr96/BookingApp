package com.irctc.booking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.booking.model.BookedSeats;
import com.irctc.booking.model.Booking;
import com.irctc.booking.model.BookingStatus;
import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Train;
import com.irctc.booking.model.User;
import com.irctc.booking.repository.BookedSeatsRepository;
import com.irctc.booking.repository.BookingRepository;

@Service
public class BookingService 
{
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	BookedSeatsRepository bookedSeatsRepo;
	
	@Autowired
	SeatService seatSer;

	public Booking bookSeats(Train train, Coach coach, List<Long> seatIds, User user) 
	{
		Booking createdBooking = createBooking(train, coach, user);
		List<BookedSeats> bookedSeats = bookSeats(createdBooking, seatIds);		
		seatSer.updateSeatsAsBooked(seatIds);
		bookedSeatsRepo.saveAll(bookedSeats);
		return createdBooking;
	}
	
	private Booking createBooking(Train train, Coach coach, User user)
	{
		Booking booking = new Booking();
		booking.setCoach(coach);
		booking.setUser(user);
		booking.setTrain(train);
		booking.setStatus(BookingStatus.CONFIRMED);
		return bookingRepo.save(booking);		
	}
	
	private List<BookedSeats> bookSeats(Booking createdBooking, List<Long> seatIds)
	{
		List<BookedSeats> bookedSeats = new ArrayList<>();			
		for(Long seatId : seatIds) {
			BookedSeats bookedSeat = populateBookedSeat(createdBooking, seatId);
			bookedSeats.add(bookedSeat);
		}
		return bookedSeats;
	}
	
	private BookedSeats populateBookedSeat(Booking createdBooking, Long seatId)
	{
		Date bookedAt = new Date();	
		BookedSeats bookedSeat = new BookedSeats();
		bookedSeat.setBookingId(createdBooking.getBookingId());
		bookedSeat.setSeatId(seatId);
		bookedSeat.setBookedAt(bookedAt);
		return bookedSeat;
	}
}
