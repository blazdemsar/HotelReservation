package com.blazdemsar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Guest;
import com.blazdemsar.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public Guest save(Guest guest) {
		return guestRepository.save(guest);
	}

	@Override
	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	@Override
	public void deleteById(int guestId) {
		guestRepository.deleteById(guestId);
	}

}
