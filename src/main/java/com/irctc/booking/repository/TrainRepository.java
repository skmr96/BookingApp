package com.irctc.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.booking.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long>
{

}
