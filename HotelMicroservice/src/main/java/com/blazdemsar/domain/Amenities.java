package com.blazdemsar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Amenities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int a_id;
    private String name;    
    private int hotelAmenity;
    
    public Amenities () {
    	
    }

	public Amenities(int a_id, String name, int hotelAmenity) {
		super();
		this.a_id = a_id;
		this.name = name;
		this.hotelAmenity = hotelAmenity;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int isHotelAmenity() {
		return hotelAmenity;
	}

	public void setHotelAmenity(int hotelAmenity) {
		this.hotelAmenity = hotelAmenity;
	}

	@Override
	public String toString() {
		return "Amenities [a_id=" + a_id + ", name=" + name + ", hotelAmenity=" + hotelAmenity + "]";
	}
    
    
    
}
