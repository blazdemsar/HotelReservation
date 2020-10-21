package com.blazdemsar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blazdemsar.domain.Booking;

@Repository
public class BookingDaoImpl implements BookingDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Booking> findByUserId(Long userId) {
		
		System.out.println("Inside of BookingDaoImpl.findByUserId().........");
		
		String sql = "SELECT b.*, h.hotelName FROM travelgig.booking b, travelgig.hotel h where userId=? and b.hotelId=h.hotelId";
		
		Object[] args = {userId};
		
		List<Booking> bookings = jdbcTemplate.query(sql, args, new BookingMapper());
		System.out.println(bookings);
		
		return bookings;
	}
	
	
}

class BookingMapper implements RowMapper<Booking> {
	
	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Booking booking = new Booking();
		booking.setBookingId(rs.getInt("bookingId"));
		booking.setHotelId(rs.getInt("hotelId"));
		booking.setBookedOnDate(rs.getString("bookedOnDate"));
		booking.setCheckInDate(rs.getString("checkInDate"));
		booking.setCheckOutDate(rs.getString("checkOutDate"));
		booking.setCustomerMobile(rs.getString("customerMobile"));
		booking.setDiscount(rs.getFloat("discount"));
		booking.setHotelRoomId(rs.getInt("hotelRoomId"));
		booking.setNoRooms(rs.getInt("noRooms"));
		booking.setPrice(rs.getFloat("price"));
		booking.setRoomType(rs.getString("roomType"));
		booking.setStatus(rs.getString("status"));
		booking.setUserId(rs.getLong("userId"));
		booking.setHotelName(rs.getString("hotelName"));
		
		return booking;
	}
}
