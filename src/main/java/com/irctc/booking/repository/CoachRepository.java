package com.irctc.booking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Seat;
import com.irctc.booking.model.Train;

public interface CoachRepository extends JpaRepository<Coach, Long>
{
	public List<Coach> findAllByCoachIdInAndTrain(List<Long> coachIds, Train train);
	
	public List<Coach> findAllByTrain(Train train);
	
	public List<Seat> findByCoachIdIn(List<Long> coachIds);
	
	@Transactional
	@Modifying
	@Query("update Coach set coach_type = :coachType where coach_id = :id")
	public void updateCoach(@Param(value = "coachType") Integer coachType,@Param(value = "id") Long id);
}
