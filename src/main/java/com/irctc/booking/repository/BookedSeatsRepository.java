package com.irctc.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.booking.model.BookedSeats;

public interface BookedSeatsRepository extends JpaRepository<BookedSeats, Long>
{

}
