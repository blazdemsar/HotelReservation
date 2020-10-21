package com.blazdemsar.domain;

public class RoomInventory {
	
	private int roomId;
	private int noOccupiedRooms;
	
	public RoomInventory() {
		super();
	}

	public RoomInventory(int roomId, int noOccupiedRooms) {
		super();
		this.roomId = roomId;
		this.noOccupiedRooms = noOccupiedRooms;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getNoOccupiedRooms() {
		return noOccupiedRooms;
	}

	public void setNoOccupiedRooms(int noOccupiedRooms) {
		this.noOccupiedRooms = noOccupiedRooms;
	}

	@Override
	public String toString() {
		return "RoomInventory [roomId=" + roomId + ", noOccupiedRooms=" + noOccupiedRooms + "]";
	}
	
	
	
}
