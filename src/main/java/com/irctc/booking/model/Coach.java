package com.irctc.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Coaches")
@Getter
@Setter
@ToString
public class Coach {
	
	@Id
	@GeneratedValue
	private Long coachId;
	@Enumerated
	private CoachType coachType;
	private int noOfSeats;
	
	@OneToMany(mappedBy="coach",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Seat> seats;
	
	@OneToMany(mappedBy="coach", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Booking> bookings;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="train_id", referencedColumnName = "trainId")
	@JsonIgnore
	private Train train;
	
	

}
