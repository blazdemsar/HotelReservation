package com.blazdemsar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity  // Specifies that the class is an entity.
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelId;
	private String hotelName;
	
	@Embedded
	private HotelAddress hotelAddress;
	
	private String email;
	private String mobileNumber;
	private String imageUrl;
	private int star;
	private double avgPrice;
	private String status;
	private String description;
	private int timesBooked;

	@OneToMany
	private Set<HotelRoom> hotelRooms = new HashSet<>();

	@ManyToMany
	private Set<Amenities> amenities = new HashSet<>();

	public Hotel () {
		
	}

	public Hotel(int hotelId, String hotelName, HotelAddress hotelAddress, String email, String mobileNumber,
			String imageUrl, int star, double avgPrice, String status, String description, int timesBooked,
			Set<HotelRoom> hotelRooms, Set<Amenities> amenities) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.imageUrl = imageUrl;
		this.star = star;
		this.avgPrice = avgPrice;
		this.status = status;
		this.description = description;
		this.timesBooked = timesBooked;
		this.hotelRooms = hotelRooms;
		this.amenities = amenities;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public HotelAddress getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(HotelAddress hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTimesBooked() {
		return timesBooked;
	}

	public void setTimesBooked(int timesBooked) {
		this.timesBooked = timesBooked;
	}

	public Set<HotelRoom> getHotelRooms() {
		return hotelRooms;
	}

	public void setHotelRooms(Set<HotelRoom> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}

	public Set<Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenities> amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", hotelAddress=" + hotelAddress + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", imageUrl=" + imageUrl + ", star=" + star + ", avgPrice="
				+ avgPrice + ", status=" + status + ", description=" + description + ", timesBooked=" + timesBooked
				+ ", hotelRooms=" + hotelRooms + ", amenities=" + amenities + "]";
	}

}
