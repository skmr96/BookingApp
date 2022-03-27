package com.irctc.booking.model;

public enum Role {
	
	ADMIN(1),
	USER(2);
	
	private Integer type;
	
	Role(int type) {
		this.type = type;
	}
	
	public Integer getType() {
		return this.type;
	}

}
