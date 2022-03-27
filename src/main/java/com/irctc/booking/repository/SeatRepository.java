package com.irctc.booking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irctc.booking.model.Coach;
import com.irctc.booking.model.Seat;
import com.irctc.booking.model.SeatStatus;

public interface SeatRepository extends JpaRepository<Seat, Long>
{
	public List<Seat> findByCoach(Coach coach);
	public List<Seat> findByCoachAndStatus(Coach coach, SeatStatus status);
	
	@Query("from Seat where coach_id in :ids")
	public List<Seat> findByCoachIdIn(List<Long> ids);
	
	@Query("from Seat where coach_id in :ids and status = :status")
	public List<Seat> findByCoachIdInAndStatus(List<Long> ids, SeatStatus status);
	
	@Transactional
	@Modifying
	@Query("update Seat set status=1 where seat_id in :ids")
	public void updateSeatsAsBooked(@Param(value = "ids") List<Long> ids);

}
