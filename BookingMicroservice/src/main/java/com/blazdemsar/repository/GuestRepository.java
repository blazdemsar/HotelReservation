package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

}
