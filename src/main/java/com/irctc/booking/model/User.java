package com.irctc.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Users")
@Getter
@Setter
@ToString
public class User 
{	
	@Id
	@GeneratedValue
	private Long userId;
	private String name;
	private Role role;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Booking> bookings;


}
