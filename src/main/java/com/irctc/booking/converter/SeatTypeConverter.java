package com.irctc.booking.converter;

import javax.persistence.AttributeConverter;

import com.irctc.booking.model.SeatStatus;

public class SeatTypeConverter implements AttributeConverter<SeatStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(SeatStatus ct) {
		return ct.getStatus();
	}

	@Override
	public SeatStatus convertToEntityAttribute(Integer dbData) {
		for(SeatStatus ct : SeatStatus.values()) {
			if(ct.getStatus().equals(dbData)) {
				return ct;
			}
		}
		return null;
	}

}
