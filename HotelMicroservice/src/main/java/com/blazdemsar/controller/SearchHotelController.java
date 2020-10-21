package com.blazdemsar.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blazdemsar.domain.Hotel;
import com.blazdemsar.domain.SearchDetails;
import com.blazdemsar.repository.HotelRepository;

public class SearchHotelController {

	/*
	 * @Autowired HotelRepository hotelRepository;
	 * 
	 * @RequestMapping(value = "/getHotels", method = RequestMethod.POST) public
	 * ResponseEntity<List<Hotel>> getResource(@RequestBody SearchDetails
	 * searchDetails) {
	 * System.out.println("searchDetails.getSearchHotel()"+searchDetails.
	 * getSearchHotel()); List<Hotel> listHotel=new ArrayList<Hotel>();
	 * listHotel.add(new Hotel(1,"Radisson","123 NewYork",5)); return new
	 * ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/getAllHotels", method = RequestMethod.GET) public
	 * ResponseEntity<List<Hotel>> getAllHotels() { List<Hotel> listHotel=new
	 * ArrayList<Hotel>(); listHotel.add(new Hotel(1,"Radisson","123 NewYork",5));
	 * listHotel.add(new Hotel(2,"Hyatt","345 NewYork",5)); listHotel.add(new
	 * Hotel(3,"Sheraton","567 NewYork",5)); return new
	 * ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/getHotelByName", method = RequestMethod.POST)
	 * public ResponseEntity<Hotel> getHotelByName(@RequestBody Hotel hotel) {
	 * System.out.println("In remote controlller..........");
	 * System.out.println("In remote controlller.........."+hotel);
	 * System.out.println("hotel received from rest client"+hotel.getHotelAddress())
	 * ; hotel.setHotelStar(5); return new ResponseEntity<Hotel>(hotel,
	 * HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/getHotelListByName", method = RequestMethod.GET)
	 * public ResponseEntity <List<Hotel>> getHotelListByName() { List<Hotel>
	 * listHotel=new ArrayList<Hotel>(); listHotel.add(new
	 * Hotel(1,"Radisson","123 NewYork", 5)); listHotel.add(new
	 * Hotel(2,"Sheraton","123 Washnigton", 8)); return new ResponseEntity
	 * <List<Hotel>>(listHotel, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/addHotel", method = RequestMethod.POST) public
	 * ResponseEntity<List<Hotel>> addHotel(@RequestBody String hotel) {
	 * System.out.println("In Microservice........."+hotel); JSONObject
	 * jsonHotel=new JSONObject(hotel); Hotel javaHotel=new Hotel();
	 * javaHotel.setHotelId(jsonHotel.getInt("hotelId"));
	 * javaHotel.setHotelName(jsonHotel.getString("hotelName"));
	 * javaHotel.setHotelAddress(jsonHotel.getString("hotelAddress"));
	 * javaHotel.setHotelStar(jsonHotel.getInt("hotelStar"));
	 * System.out.println("javaHotel....."+javaHotel.getHotelId());
	 * 
	 * List<Hotel>listHotel=new ArrayList<Hotel>(); listHotel.add(javaHotel); return
	 * new ResponseEntity<List<Hotel>>(listHotel, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/getHotelName", method = RequestMethod.POST) public
	 * ResponseEntity<List<Hotel>> getHotelName(@RequestBody String hotel) {
	 * System.out.println("In Microservice........."+hotel); JSONObject
	 * jsonHotel=new JSONObject(hotel); Hotel javaHotel=new Hotel();
	 * javaHotel.setHotelName(jsonHotel.getString("hotelName"));
	 * 
	 * System.out.println("javaHotel....."+javaHotel.getHotelName());
	 * 
	 * 
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
	 * 
	 * 
	 * List<Hotel> resultList =
	 * hotelRepository.findByHotelName(javaHotel.getHotelName()+"%");
	 * System.out.println(resultList); List<Hotel>listHotel=new ArrayList<Hotel>();
	 * listHotel.add(javaHotel); return new ResponseEntity<List<Hotel>>(resultList,
	 * HttpStatus.OK);
	 * 
	 * }
	 */
}
