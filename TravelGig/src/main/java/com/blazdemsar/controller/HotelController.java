package com.blazdemsar.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.User;
import com.blazdemsar.restclient.HotelRestClient;
import com.blazdemsar.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class HotelController {
	
	@Autowired
	HotelRestClient hotelRestClient;
	
	@Autowired
	UserService userService;
	
	/*
	 * @RequestMapping(value = "/addHotel", method = RequestMethod.POST) public
	 * ResponseEntity<String> getHotelListByName(@RequestBody String hotel) {
	 * hotelRestClient.addHotel(hotel); return new ResponseEntity<String>("Success",
	 * HttpStatus.OK);
	 * 
	 * }
	 */
	
	@RequestMapping(value="/getHotelName", method=RequestMethod.POST)
	public ResponseEntity<String> getHotelName(@RequestBody String hotel) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getHotelRooms()......." + hotel);
		
		JSONObject jsonObject = new JSONObject(hotel);
        JSONObject jsonResponseObject = hotelRestClient.getHotelName(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getHotelRooms", method=RequestMethod.POST)
	public ResponseEntity<String> getHotelRooms(@RequestBody String hotel) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getHotelRooms()......." + hotel);
		
		JSONObject jsonObject = new JSONObject(hotel);
		JSONObject jsonResponseObject = hotelRestClient.getHotelRooms(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getHotelRoomsAvailable", method=RequestMethod.POST)
	public ResponseEntity<String> getHotelRoomsAvailable(@RequestBody String hotelRoomDetails) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getHotelRoomsAvailable()......." + hotelRoomDetails);
		
		JSONObject jsonObject = new JSONObject(hotelRoomDetails);
		JSONObject jsonResponseObject = hotelRestClient.getHotelRoomsAvailable(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/saveBooking", method=RequestMethod.POST)
	public ResponseEntity<String> saveBooking(@RequestBody String booking) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.saveBooking()......." + booking);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(auth.getName());
		
		JSONObject jsonObject = new JSONObject(booking);
		
		// done for security, to make sure userId wasn't tampered with
		jsonObject.put("userId", user.getUserId());
		
		JSONObject jsonResponseObject = hotelRestClient.saveBooking(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/validateBooking", method=RequestMethod.GET)
	public ResponseEntity<String> validateBooking() {
		
		System.out.println("Inside of HotelController.validateBooking().......");
		
		return new  ResponseEntity<String>("Hello", HttpStatus.OK);
	}
	
	@RequestMapping(value="/getBookingsByUserId", method=RequestMethod.POST)
	public ResponseEntity<String> getBookingsByUserId() throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getBookingsByUserId().......");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(auth.getName());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", user.getUserId());
		
		JSONObject jsonResponseObject = hotelRestClient.getBookingsByUserId(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateBookingStatus", method=RequestMethod.POST)
	public ResponseEntity<String> updateBookingStatus(@RequestBody String booking) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.updateBookingStatus().......");
		
		JSONObject jsonObject = new JSONObject(booking);
		
		JSONObject jsonResponseObject = hotelRestClient.updateBookingStatus(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/saveReview", method=RequestMethod.POST)
	public ResponseEntity<String> saveReview(@RequestBody String hotelReview) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.saveReview()......." + hotelReview);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(auth.getName());
		
		JSONObject jsonObject = new JSONObject(hotelReview);
		
		// done for security, to make sure userId wasn't tampered with
		jsonObject.put("userName", user.getUsername());
		
		JSONObject jsonResponseObject = hotelRestClient.saveReview(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getReviewsForHotel", method=RequestMethod.POST)
	public ResponseEntity<String> getReviewsForHotel(@RequestBody String reviewDetails) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getReviewsForHotel().......");
		
		JSONObject jsonObject = new JSONObject(reviewDetails);
		System.out.println(jsonObject.getInt("hotelId"));
		
        JSONObject jsonResponseObject = hotelRestClient.getReviewsForHotel(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getHotelQA", method=RequestMethod.POST)
	public ResponseEntity<String> getHotelQA(@RequestBody String qaDetails) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getHotelQA().......");
		
		JSONObject jsonObject = new JSONObject(qaDetails);
		System.out.println(jsonObject.getInt("hotelId"));
		
        JSONObject jsonResponseObject = hotelRestClient.getHotelQA(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/saveQuestionAnswer", method=RequestMethod.POST)
	public ResponseEntity<String> saveQuestionAnswer(@RequestBody String qaDetails) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.saveQuestionAnswer()......." + qaDetails);
		
		JSONObject jsonObject = new JSONObject(qaDetails);
		System.out.println(jsonObject.getInt("hotelId"));
		
		JSONObject jsonResponseObject = hotelRestClient.saveQuestionAnswer(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllQAs", method=RequestMethod.POST)
	public ResponseEntity<String> getAllQAs() throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.getAllQAs().......");
		
        JSONObject jsonResponseObject = hotelRestClient.getAllQAs();
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateQA", method=RequestMethod.POST)
	public ResponseEntity<String> updateQA(@RequestBody String qaDetails) throws JSONException, JsonProcessingException {
		
		System.out.println("Inside of HotelController.updateQA()......." + qaDetails);
		
		JSONObject jsonObject = new JSONObject(qaDetails);
		System.out.println("qaID: " + jsonObject.getInt("qaId"));
		
		JSONObject jsonResponseObject = hotelRestClient.updateQA(jsonObject);
		
		return new  ResponseEntity<String>(jsonResponseObject.toString(), HttpStatus.OK);
	}
}
