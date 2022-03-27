package com.irctc.booking.model;

public enum CoachType {
	
	AC(0),
	NON_AC(1),
	SEATER(2);
	
	private Integer type;
	
	CoachType(int type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}
	

}
