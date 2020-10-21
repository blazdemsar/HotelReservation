package com.blazdemsar.dao;

import java.util.List;

import com.blazdemsar.domain.RoomInventory;

public interface HotelRoomDao {
	
	public List<RoomInventory> getHotelRoomsAvailable(String checkInDate, String checkOutDate);
	
}
