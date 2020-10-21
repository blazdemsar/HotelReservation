package com.blazdemsar.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.blazdemsar.domain.Booking;

public interface BookingService {
	
	public Booking save(Booking booking);
	public List<Booking> findAll();
	public void deleteById(int bookingId);
	public Booking findById(int bookingId);
	public List<Booking> findByUserId(Long userId);
	public String getUserEmail(Long userId);
	public String getUsername(Long userId);
	public String getHotelName(int hotelId);
	public String getHotelAddressLine1(int hotelId);
	public String getHotelCity(int hotelId);
	public String getHotelState(int hotelId);
	public String getHotelImage(int hotelId);
	//public Booking updateById(int bookingId);
	
}
