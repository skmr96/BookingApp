package com.irctc.booking.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Bookings")
@Getter
@Setter
@ToString
public class Booking {
	
	@Id
	@GeneratedValue
	private Long bookingId;
	private BookingStatus status;
	
	@ManyToOne
	@JoinColumn(name="train_id")
	private Train train;
	
	@ManyToOne
	@JoinColumn(name="coach_id")
	private Coach coach;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Transient
	private List<Integer> seatNos;

}
