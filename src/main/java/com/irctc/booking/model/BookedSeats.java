package com.irctc.booking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="BookedSeats")
@Getter
@Setter
public class BookedSeats {
	
	@Id
	@GeneratedValue
	private Long bookedSeatId;
	private Long bookingId;
	private Long seatId;
	private Date bookedAt;

}
