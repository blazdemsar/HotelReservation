package com.blazdemsar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blazdemsar.domain.RoomInventory;

@Repository
public class HotelRoomDaoImpl implements HotelRoomDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RoomInventory> getHotelRoomsAvailable(String checkInDate, String checkOutDate) {
		
		System.out.println("Inside of HotelRoomDaoImpl.getHotelRoomsAvailable().........");
		
		String sql = "SELECT hotelRoomId, SUM(noRooms) nrOccupiedRooms FROM travelgig.booking  WHERE" + 
					 "  ( ? >= checkInDate AND ? <  checkOutDate)" + 
					 "  OR ( ? >= checkInDate AND ? <= checkOutDate)" + 
					 "  GROUP BY hotelRoomId";
		
		//checkInDate = checkInDate.replaceAll("-", "");
		
		Object[] args = {checkInDate, checkInDate, checkOutDate, checkOutDate};
		
		List<RoomInventory> availableRooms = jdbcTemplate.query(sql, args, new RoomMapper());
		
		return availableRooms;
	}

}

class RoomMapper implements RowMapper<RoomInventory> {
	
	@Override
	public RoomInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RoomInventory roomInventory = new RoomInventory();
		roomInventory.setRoomId(rs.getInt("hotelRoomId"));
		roomInventory.setNoOccupiedRooms(rs.getInt("nrOccupiedRooms"));
		
		return roomInventory;
	}
}