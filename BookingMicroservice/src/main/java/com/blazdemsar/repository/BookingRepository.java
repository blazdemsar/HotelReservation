package com.blazdemsar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blazdemsar.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	public List<Booking> findByUserId(Long userId);
	
	@Query(value="SELECT email FROM travelgig.user WHERE userId=:userId", nativeQuery=true)
	public String getUserEmail(Long userId);
	
	@Query(value="SELECT username FROM travelgig.user WHERE userId=:userId", nativeQuery=true)
	public String getUsername(Long userId);
	
	@Query(value="SELECT hotelName FROM travelgig.hotel WHERE hotelId=:hotelId", nativeQuery=true)
	public String getHotelName(int hotelId);
	
	@Query(value="SELECT addressLine1 FROM travelgig.hotel WHERE hotelId=:hotelId", nativeQuery=true)
	public String getHotelAddressLine1(int hotelId);
	
	@Query(value="SELECT city FROM travelgig.hotel WHERE hotelId=:hotelId", nativeQuery=true)
	public String getHotelCity(int hotelId);
	
	@Query(value="SELECT state FROM travelgig.hotel WHERE hotelId=:hotelId", nativeQuery=true)
	public String getHotelState(int hotelId);
	
	@Query(value="SELECT imageUrl FROM travelgig.hotel WHERE hotelId=:hotelId", nativeQuery=true)
	public String getHotelImage(int hotelId);
	
}
