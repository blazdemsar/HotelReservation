package com.blazdemsar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blazdemsar.domain.HotelReview;

public interface HotelReviewRepository extends JpaRepository<HotelReview, Integer> {
	
	@Query(value="SELECT * FROM travelgig.hotelreview WHERE hotelId=:hotelId", nativeQuery=true)
	public List<HotelReview> findAllByHotelId(int hotelId);
	
}
