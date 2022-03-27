package com.irctc.booking.model;

public enum SeatStatus {
	
	AVAILABLE(0),
	BOOKED(1);
	
	private Integer status; 
	
	SeatStatus(int status) {
		this.status = status;		
	}
	
	public Integer getStatus() {
		return this.status;
	}

}
