package com.blazdemsar.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HotelReview {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hotelReviewId;
    private int hotelId;
    private String userName;
    private LocalDate reviewDate;
    private int roomRating;
    private int foodRating;
    private int cleanlinessRating;
    private int valueRating;
    private int serviceRating;
    private String description;
    
    public HotelReview() {
    	super();
    }

	public HotelReview(int hotelReviewId, int hotelId, String userName, LocalDate reviewDate, int roomRating,
			int foodRating, int cleanlinessRating, int valueRating, int serviceRating, String description) {
		super();
		this.hotelReviewId = hotelReviewId;
		this.hotelId = hotelId;
		this.userName = userName;
		this.reviewDate = reviewDate;
		this.roomRating = roomRating;
		this.foodRating = foodRating;
		this.cleanlinessRating = cleanlinessRating;
		this.valueRating = valueRating;
		this.serviceRating = serviceRating;
		this.description = description;
	}

	public int getHotelReviewId() {
		return hotelReviewId;
	}

	public void setHotelReviewId(int hotelReviewId) {
		this.hotelReviewId = hotelReviewId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getRoomRating() {
		return roomRating;
	}

	public void setRoomRating(int roomRating) {
		this.roomRating = roomRating;
	}

	public int getFoodRating() {
		return foodRating;
	}

	public void setFoodRating(int foodRating) {
		this.foodRating = foodRating;
	}

	public int getCleanlinessRating() {
		return cleanlinessRating;
	}

	public void setCleanlinessRating(int cleanlinessRating) {
		this.cleanlinessRating = cleanlinessRating;
	}

	public int getValueRating() {
		return valueRating;
	}

	public void setValueRating(int valueRating) {
		this.valueRating = valueRating;
	}

	public int getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(int serviceRating) {
		this.serviceRating = serviceRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "HotelReview [hotelReviewId=" + hotelReviewId + ", hotelId=" + hotelId + ", userName=" + userName
				+ ", reviewDate=" + reviewDate + ", roomRating=" + roomRating + ", foodRating=" + foodRating
				+ ", cleanlinessRating=" + cleanlinessRating + ", valueRating=" + valueRating + ", serviceRating="
				+ serviceRating + ", description=" + description + "]";
	}
    
}
