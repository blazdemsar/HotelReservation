package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.HotelReview;

public interface HotelReviewService {
	
	public HotelReview save(HotelReview hotelReview);
	public List<HotelReview> findAll();
	public void deleteById(int hotelReviewId);
	public HotelReview findById(int hotelReviewId);
	public List<HotelReview> findAllByHotelId(int hotelId);
}
