package com.blazdemsar.dao;
import java.util.List;

import com.blazdemsar.domain.Booking;

public interface BookingDao {
	
	public List<Booking> findByUserId(Long userId);
}
