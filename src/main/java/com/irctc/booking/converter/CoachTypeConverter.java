package com.irctc.booking.converter;

import javax.persistence.AttributeConverter;

import com.irctc.booking.model.CoachType;

public class CoachTypeConverter implements AttributeConverter<CoachType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(CoachType ct) {
		return ct.getType();
	}

	@Override
	public CoachType convertToEntityAttribute(Integer dbData) {
		for(CoachType ct : CoachType.values()) {
			if(ct.getType().equals(dbData)) {
				return ct;
			}
		}
		return null;
	}

}
