package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Booking;
import com.blazdemsar.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public Booking save(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public void deleteById(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	@Override
	public Booking findById(int bookingId) {
		
		Optional<Booking> optBooking = bookingRepository.findById(bookingId);
		
		if (optBooking.isPresent()) {
			
			return optBooking.get();
			
		}
		
		return null;
	}

	@Override
	public List<Booking> findByUserId(Long userId) {
		return bookingRepository.findByUserId(userId);
	}

	@Override
	public String getUserEmail(Long userId) {
		return bookingRepository.getUserEmail(userId);
	}

	@Override
	public String getUsername(Long userId) {
		return bookingRepository.getUsername(userId);
	}

	@Override
	public String getHotelName(int hotelId) {
		return bookingRepository.getHotelName(hotelId);
	}

	@Override
	public String getHotelAddressLine1(int hotelId) {
		return bookingRepository.getHotelAddressLine1(hotelId);
	}

	@Override
	public String getHotelCity(int hotelId) {
		return bookingRepository.getHotelCity(hotelId);
	}

	@Override
	public String getHotelState(int hotelId) {
		return bookingRepository.getHotelState(hotelId);
	}

	@Override
	public String getHotelImage(int hotelId) {
		return bookingRepository.getHotelImage(hotelId);
	}
	
	
	
	

}
