package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Hotel;

public interface HotelService {
	
	public List<Hotel> findByHotelKey(String hotelKey);
	
}
