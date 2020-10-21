package com.blazdemsar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Hotel;
import com.blazdemsar.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public List<Hotel> findByHotelKey(String hotelKey) {
		
		return hotelRepository.findByHotelKey(hotelKey);
		
	}

}
