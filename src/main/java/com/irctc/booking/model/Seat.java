package com.irctc.booking.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Seats")
@Getter
@Setter
@ToString
public class Seat 
{
	@Id
	@GeneratedValue
	private Long seatId;
	private int seatNo;
	@Enumerated
	private SeatStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="coach_id")
	private Coach coach;

}
