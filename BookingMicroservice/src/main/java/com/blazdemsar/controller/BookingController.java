package com.blazdemsar.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.dao.BookingDao;
import com.blazdemsar.domain.Booking;
import com.blazdemsar.service.BookingService;
import com.blazdemsar.service.MailService;
import com.itextpdf.text.DocumentException;

@CrossOrigin
@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping(value="/saveBooking", method=RequestMethod.POST)
	public ResponseEntity<?> saveBooking(@RequestBody Booking booking) throws DocumentException, IOException, MessagingException {
		
		System.out.println("Inside of BookingController.saveBooking()....." + booking.toString());
		
		Booking bookingFromDb = bookingService.save(booking);
		System.out.println(bookingFromDb);
		
		if (bookingFromDb != null) {
			
			String emailTo = bookingService.getUserEmail(booking.getUserId());
			String username = bookingService.getUsername(booking.getUserId());
			String hotelName = bookingService.getHotelName(booking.getHotelId());
			String hotelAddrL1 = bookingService.getHotelAddressLine1(booking.getHotelId());
			String hotelCity = bookingService.getHotelCity(booking.getHotelId());
			String hotelState = bookingService.getHotelState(booking.getHotelId());
			String hotelImage = bookingService.getHotelImage(booking.getHotelId());
			
			mailService.bookingConfirmationPdfCreator(booking, emailTo, username, hotelName, hotelAddrL1, hotelCity, hotelState, hotelImage);
			
			
			//SimpleMailMessage message = mailService.sendEmail(user.getName(), user.getEmail());
			
			return new ResponseEntity<Booking>(bookingFromDb, HttpStatus.OK);
		
		} else {
			
			return new ResponseEntity<String>("Booking Microservice could not save the booking!", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	@RequestMapping(value="/getBookingsByUserId", method=RequestMethod.POST)
	public ResponseEntity<List<Booking>> getBookingsByUserId(@RequestBody String userIdString) {
		
		System.out.println("Inside of BookingController.getAllBookings()....." + userIdString);
		
		JSONObject jsonObject = new JSONObject(userIdString);
		
		Long userId = jsonObject.getLong("userId");
		
		System.out.println(userId);
		
		List<Booking> bookings = bookingDao.findByUserId(userId);
		
		System.out.println(bookings);
		
		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value="/updateBookingStatus", method=RequestMethod.POST)
	public ResponseEntity<?> updateBookingStatus(@RequestBody String booking) {
		
		System.out.println("Inside of BookingController.saveBooking()....." + booking.toString());
		
		JSONObject jsonObject = new JSONObject(booking);
		
		int bookingId = jsonObject.getInt("bookingId");
		
		Booking bookingFromDb = bookingService.findById(bookingId);
		
		bookingFromDb.setStatus("Canceled");
		
		Booking updatedBooking = bookingService.save(bookingFromDb);
		System.out.println(updatedBooking);
		
		if (updatedBooking != null) {
			return new ResponseEntity<Booking>(updatedBooking, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Booking Microservice could not update the booking!", HttpStatus.NOT_IMPLEMENTED);
		}
		
	}

}
