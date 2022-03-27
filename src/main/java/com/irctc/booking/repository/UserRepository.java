package com.irctc.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.booking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
