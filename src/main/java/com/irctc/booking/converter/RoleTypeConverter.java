package com.irctc.booking.converter;

import javax.persistence.AttributeConverter;

import com.irctc.booking.model.Role;

public class RoleTypeConverter implements AttributeConverter<Role, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Role ct) {
		return ct.getType();
	}

	@Override
	public Role convertToEntityAttribute(Integer dbData) {
		for(Role ct : Role.values()) {
			if(ct.getType().equals(dbData)) {
				return ct;
			}
		}
		return null;
	}

}
