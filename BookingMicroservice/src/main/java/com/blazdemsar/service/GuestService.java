package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Guest;

public interface GuestService {
	
	public Guest save(Guest guest);
	public List<Guest> findAll();
	public void deleteById(int guestId);
	//public Booking updateById(int bookingId);
	
}
