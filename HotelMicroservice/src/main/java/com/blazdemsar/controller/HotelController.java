package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.blazdemsar.domain.SearchDetails;
import com.blazdemsar.repository.HotelRepository;

@CrossOrigin
@RestController
public class HotelController {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@RequestMapping(value = "/getHotelName", method = RequestMethod.POST)
	public ResponseEntity<List<Hotel>> getHotelName(@RequestBody String hotel) {
		
		System.out.println("In Microservice........."+hotel);
		
		JSONObject jsonObject = new JSONObject(hotel);
		System.out.println(jsonObject.getString("hotelKey"));
		//Hotel javaHotel=new Hotel();
		//javaHotel.setHotelName(jsonHotel.getString("hotelName"));
		
		//System.out.println("javaHotel....."+javaHotel.getHotelName());
		
		/*
		 * List<Hotel> hotelName = new ArrayList<Hotel>(); List<Hotel> resultList = new
		 * ArrayList<Hotel>(); hotelName.add(new Hotel(1,"Radisson")); hotelName.add(new
		 * Hotel(2,"Hyatt")); hotelName.add(new Hotel(3,"Lee Meridian"));
		 * hotelName.add(new Hotel(4,"Mariot")); hotelName.add(new Hotel(5,"Taj"));
		 * hotelName.add(new Hotel(6,"Lalit")); hotelName.add(new Hotel(7,"Sheratton"));
		 * hotelName.add(new Hotel(8,"Shyam")); hotelName.add(new Hotel(9,"Sharyy"));
		 * hotelName.add(new Hotel(10,"Shola"));
		 * 
		 * Iterator<Hotel> itr = hotelName.iterator(); while(itr.hasNext()) { Hotel
		 * iteratedHotel = itr.next(); String hotelItr = iteratedHotel.getHotelName();
		 * if(hotelItr.startsWith(javaHotel.getHotelName())) {
		 * 
		 * resultList.add(iteratedHotel);
		 * 
		 * }
		 * 
		 * }
		 */
		
		List<Hotel> resultList = hotelRepository.findByHotelKey(jsonObject.getString("hotelKey")+"%");
		System.out.println(resultList);

		return new  ResponseEntity<List<Hotel>>(resultList, HttpStatus.OK);
		
	}
}
