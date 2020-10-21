package com.blazdemsar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blazdemsar.domain.Hotel;
import com.blazdemsar.domain.HotelRoom;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{
	
	@Query(value = "select * from hotel where HOTELNAME like :hotelKey OR CITY like :hotelKey OR STATE like :hotelKey OR addressLine1 like :hotelKey", nativeQuery=true)
	public List<Hotel> findByHotelKey(String hotelKey);
    //select * from hotel where hotelname like 'Rad%' or city like 'new%' or area like 'hit%';
	
	/*
	 * @Query(value = "select * from hotelroom where TOTALROOM = :nrOfRooms") public
	 * List<Hotel> findByNrOfRooms(int nrOfRooms);
	 * 
	 * @Query(value = "") public List<Hotel> findByNrOfGuests(int nrOfGuests);
	 */
	
	@Query(value="select * from hotel where HOTELID=:hotelId", nativeQuery=true)
	public Hotel findByHotelId(int hotelId);
	
	@Query(value="select hotelRoomId, noRooms from booking where (checkInDate between :checkInDate and :checkOutDate) or (checkOutDate between :checkInDate and :checkOutDate) and hotelId=:hotelId;", nativeQuery=true)
	public List<HotelRoom> findByCheckInOutDate(int hotelId, String checkInDate, String checkOutDate);
	
	/*
	 String sql = "SELECT HR.roomId, HR.totalRoom - SUM(B.norooms) availableRooms FROM Booking B, HotelRoom HR WHERE HR.roomID=B.hotelRoomID(+) "
                         + "AND ? >= TO_CHAR(B.checkindate,'YYYYMMDD')  "
                         + "AND ? <  TO_CHAR(B.checkoutdate,'YYYYMMDD')"
                         + "GROUP BY HR.roomID, HR.totalRoom  "
                         + "HAVING SUM(norooms) < HR.totalRoom";
	 */
	
}
