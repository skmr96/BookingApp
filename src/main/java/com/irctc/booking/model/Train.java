package com.irctc.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Trains")
@Getter
@Setter
@ToString
public class Train {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(initialValue = 100, name = "Train_ID")
	@Column(name = "trainId", unique = true, nullable = false)
	private Long trainId;
	private String name;
	
	@OneToMany(mappedBy="train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Coach> coaches;
	
	@OneToMany(mappedBy="train", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Booking> bookings;

	
	

}
