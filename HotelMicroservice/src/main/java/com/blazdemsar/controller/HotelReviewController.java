package com.blazdemsar.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.Hotel;
import com.blazdemsar.domain.HotelReview;
import com.blazdemsar.domain.HotelRoom;
import com.blazdemsar.service.HotelReviewService;

@CrossOrigin
@RestController
public class HotelReviewController {
	
	@Autowired
	HotelReviewService hotelReviewService;
	
	@RequestMapping(value="/saveReview", method=RequestMethod.POST)
	public ResponseEntity<?> saveReview(@RequestBody HotelReview hotelReview) {
		
		System.out.println("Inside of HotelReviewController.saveReview()....." + hotelReview);
		
		HotelReview hotelReviewFromDb = hotelReviewService.save(hotelReview);
		System.out.println(hotelReviewFromDb);
		
		if (hotelReviewFromDb != null) {
			
			return new ResponseEntity<HotelReview>(hotelReviewFromDb, HttpStatus.OK);
		
		} else {
			
			return new ResponseEntity<String>("Hotel Microservice could not save the review!", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	@RequestMapping(value="/getReviewsForHotel", method=RequestMethod.POST)
	public ResponseEntity<List<HotelReview>> getReviewsForHotel(@RequestBody String reviewDetails) {
		
		System.out.println("In Microservice... " + reviewDetails);
		JSONObject jsonHotelReview = new JSONObject(reviewDetails);
		
		List<HotelReview> hotelReviews = hotelReviewService.findAllByHotelId(jsonHotelReview.getInt("hotelId"));
		
		System.out.println(hotelReviews);
		
		return new ResponseEntity<List<HotelReview>>(hotelReviews, HttpStatus.OK);
	}
}
