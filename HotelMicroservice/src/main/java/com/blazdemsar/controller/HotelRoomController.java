package com.blazdemsar.controller;

import java.util.HashSet;
import java.util.Iterator;
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

import com.blazdemsar.dao.HotelRoomDao;
import com.blazdemsar.domain.Hotel;
import com.blazdemsar.domain.HotelRoom;
import com.blazdemsar.domain.RoomInventory;
import com.blazdemsar.repository.HotelRepository;

@CrossOrigin
@RestController
public class HotelRoomController {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	HotelRoomDao hotelRoomDao;
	
	@RequestMapping(value="/getHotelRooms", method=RequestMethod.POST)
	public ResponseEntity<Set<HotelRoom>> getHotelRooms(@RequestBody String hotel) {
		
		System.out.println("In Microservice... " + hotel);
		JSONObject jsonHotel = new JSONObject(hotel);
		
		Hotel javaHotel = new Hotel();
		javaHotel.setHotelId(Integer.parseInt(jsonHotel.getString("hotelId")));
		
		Optional<Hotel> hotelFromDb = hotelRepository.findById(javaHotel.getHotelId());
		
		Set<HotelRoom> hotelRooms = hotelFromDb.get().getHotelRooms();
		
		return new ResponseEntity<Set<HotelRoom>>(hotelRooms, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getHotelRoomsAvailable", method=RequestMethod.POST)
	public ResponseEntity<Set<HotelRoom>> getHotelRoomsAvailable(@RequestBody String hotelRoomDetails) {
		
		System.out.println("In Microservice... " + hotelRoomDetails);
		JSONObject jsonHotelRoomDetails = new JSONObject(hotelRoomDetails);
		
		Set<HotelRoom> hotelRoomsFromDb = new HashSet<>();
		
		int hotelId = jsonHotelRoomDetails.getInt("hotelId");
		String checkInDate = jsonHotelRoomDetails.getString("checkInDate");
		String checkOutDate = jsonHotelRoomDetails.getString("checkOutDate");
		int noRooms = jsonHotelRoomDetails.getInt("noRooms");
		
		List<RoomInventory> roomInventory = hotelRoomDao.getHotelRoomsAvailable(checkInDate, checkOutDate);
		System.out.println(roomInventory);
		
		Optional<Hotel> hotelFromDb = hotelRepository.findById(hotelId);
		
		Set<HotelRoom> hotelRooms = hotelFromDb.get().getHotelRooms();
		
		Set<HotelRoom> hotelRoomsAvailable = new HashSet<>();
		
		for (HotelRoom room : hotelRooms) {
			
			System.out.println("Current hotel room: " + room);
			
			if (roomInventory.size() > 0) {
				
				for (int i = 0; i < roomInventory.size(); i++) {
				
					RoomInventory tempRoomInv = roomInventory.get(i);
				
					System.out.println("Current tempRoomInv: " + tempRoomInv);
				
					if (room.getRoomId() == tempRoomInv.getRoomId()) {
					
						if ((room.getTotalroom() - tempRoomInv.getNoOccupiedRooms()) >= noRooms) {
							
							hotelRoomsAvailable.add(room);
							
						}
					
					} else {
					
						hotelRoomsAvailable.add(room);
					
					}
				
				}
			
			} else {
				
				hotelRoomsAvailable.add(room);
				
			}
		}
		
		System.out.println("Hotel Rooms Available: " + hotelRoomsAvailable.toString());
		
		return new ResponseEntity<Set<HotelRoom>>(hotelRoomsAvailable, HttpStatus.OK);
	}
}
