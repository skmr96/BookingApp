package com.irctc.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.booking.model.Booking;
import com.irctc.booking.model.User;

public interface BookingRepository extends JpaRepository<Booking, Long>
{
	public List<Booking> findAByUser(User user);

}
