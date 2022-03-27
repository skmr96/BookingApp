package com.irctc.booking.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Train;

public class CoachUtils 
{
	private CoachUtils() {}
	
	private static final String INVALID_COACHES = "Coaches not present in the Given Train"; 
	
	public static void doesCoachesExistInTheTrain(List<Coach> coaches, Train train) throws ValidationException 
	{
		List<Coach> existingCoaches = train.getCoaches();
		List<Long> existingCoachIds = existingCoaches.stream().map(Coach::getCoachId).collect(Collectors.toList());
		List<Long> givenCoachIds = coaches.stream().map(Coach::getCoachId).collect(Collectors.toList());
		if (!existingCoachIds.containsAll(givenCoachIds)) {
			throw new ValidationException(INVALID_COACHES);
		}	
	}

}
