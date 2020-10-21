package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.HotelReview;
import com.blazdemsar.repository.HotelReviewRepository;

@Service
public class HotelReviewServiceImpl implements HotelReviewService {
	
	@Autowired
	HotelReviewRepository hotelReviewRepository;
	
	@Override
	public HotelReview save(HotelReview hotelReview) {
		return hotelReviewRepository.save(hotelReview);
	}

	@Override
	public List<HotelReview> findAll() {
		return hotelReviewRepository.findAll();
	}

	@Override
	public void deleteById(int hotelReviewId) {
		hotelReviewRepository.deleteById(hotelReviewId);
	}

	@Override
	public HotelReview findById(int hotelReviewId) {
		
		Optional<HotelReview> optReview = hotelReviewRepository.findById(hotelReviewId);
		
		if (optReview.isPresent()) {
			
			return optReview.get();
		}
		
		return null;
	}

	@Override
	public List<HotelReview> findAllByHotelId(int hotelId) {
		return hotelReviewRepository.findAllByHotelId(hotelId);
	}

}
