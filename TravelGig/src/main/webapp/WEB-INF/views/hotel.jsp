<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page of Travel Gig</title>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/hotel.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="home.css"> -->
</head>
<body>
	<div class="container rounded"
		style="margin: auto; padding: 50px; margin-top: 50px; margin-bottom: 50px; background-image: url('./images/WhyPeopleTravel_main.jpg');">
		<div class="row" id='demo1Div'>
			<div class="col-6">
				<div class="container" align="center" style="margin-top: 40px;">
					<h1>Welcome to Travel Gig</h1>
					<h3>Search your next destination</h3>
				</div>
			</div>
			<div class="col-6">
				<div class="container" align="center" style="margin-top: 45px;">
					<c:choose>
						<c:when test="${currentUser == null}">
							<a href="login" class="btn btn-warning">Login</a>
							<a href="user" class="btn btn-warning">Create Account</a>
						</c:when>
						<c:otherwise>
							<h3>Hello ${currentUser.username}, welcome back!</h3>
							<a href="login?logout" class="btn btn-warning">Logout</a>
							<a href="#" class="btn btn-warning" id="booking_history">Your
								Bookings</a>
							<sec:authorize
								access="hasAuthority('Administrator') || hasAuthority('Manager')">
								<a href="admin" class="btn btn-warning">Admin</a>
								<a href="role" class="btn btn-warning">Role</a>
							</sec:authorize>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="row" id='demo1Div'>
			<div class="container rounded"
				style="margin: auto; padding: 50px; margin-top: 50px; margin-bottom: 50px; background-color: rgba(204, 230, 255, 0.6);">
				<h3>Narrow your search results</h3>
				<div class="form-row">
					<div class="col-3">
						Hotel/City/State/Address <input class="form-control" type="text"
							id="searchLocation" name="searchLocation" />
					</div>
					<div class="col-2">
						No. Rooms: <input class="form-control" type="number" id="noRooms"
							name="noRooms" />
					</div>
					<div class="col-2">
						No. Guests: <input class="form-control" type="number"
							id="noGuests" name="noGuests" />
					</div>
					<div class="col">
						Check-In Date: <input type="date" id="checkInDate"
							name="checkInDate" />
					</div>
					<div class="col">
						Check-Out Date: <input type="date" id="checkOutDate"
							name="checkOutDate" />
					</div>
					<input class="btn btn-warning sbtButton" type="button"
						id="searchBtn" value="SEARCH" />
				</div>
			</div>
		</div>
	</div>

	<div class="container rounded"
		style="margin: auto; padding: 50px; margin-top: 50px; margin-bottom: 50px; background-color: rgba(217, 217, 217, 0.2)">
		<div class="row" id='demo1Div'>
			<div class="col-3">
				<div class="container rounded">
					<div class="row border rounded"
						style="margin: auto; margin: 10px 0; padding: 16px;">
						<div class="row">
							<div class="col-12">
								Star Rating:<br/><br/>
								<div class="form-check-inline">
									<label class="form-check-label"><input type="checkbox"
										class="star_rating form-check-input" id="1_star_rating"
										value=1>1 </label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"><input type="checkbox"
										class="star_rating form-check-input" id="2_star_rating"
										value=2>2 </label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"><input type="checkbox"
										class="star_rating form-check-input" id="3_star_rating"
										value=3>3 </label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"><input type="checkbox"
										class="star_rating form-check-input" id="4_star_rating"
										value=4>4 </label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"> <input type="checkbox"
										class="star_rating form-check-input" id="5_star_rating"
										value=5>5
									</label>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-12">
								<br/>Range:<br/>
								<div class="slidecontainer">
									<input type="range" class="slider" style="width: 100%" min="1" max="500" value="500" 
									id="priceRange">
								</div>
								<p>Price: $<span id="priceValue"></span></p>
							</div>
						</div>

						<br />Hotel Amenities:
						<div class="form-check">
							<input type="checkbox" class="hotel_amenity form-check-input"
								id="amenity_all_inclusive" value="ALL-INCLUSIVE" /> <label
								class="form-check-label" for="amenity_all_inclusive">All-Inclusive</label>
							<br> <input type="checkbox"
								class="hotel_amenity form-check-input" id="amenity_wi_fi"
								value="WI-FI" /> <label class="form-check-label"
								for="amenity_wi_fi">Wi-Fi</label> <br> <input
								type="checkbox" class="hotel_amenity form-check-input"
								id="amenity_air-conditioning" value="AIR CONDITIONING" /> <label
								class="form-check-label" for="amenity_air-conditioning">Air
								Conditioning</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input"
								id="amenity_gaming_lounge" value="GAMING LOUNGE" /> <label
								class="form-check-label" for="amenity_gaming_lounge">Gaming
								Lounge</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input"
								id="amenity_dining_lounge" value="DINING LOUNGE" /> <label
								class="form-check-label" for="amenity_dining_lounge">Dining
								Lounge</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input"
								id="amenity_meeting_rooms" value="MEETING ROOMS" /> <label
								class="form-check-label" for="amenity_meeting_rooms">Meeting
								Rooms</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input"
								id="amenity_public_restrooms" value="PUBLIC RESTROOMS" /> <label
								class="form-check-label" for="amenity_public_restrooms">Public
								Restrooms</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input"
								id="amenity_valet_parking" value="VALET PARKING" /> <label
								class="form-check-label" for="amenity_valet_parking">Valet
								Parking</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input"
								id="amenity_parking_garage" value="PARKING GARAGE" /> <label
								class="form-check-label" for="amenity_parking_garage">Parking
								Garage</label> <br> <input type="checkbox"
								class="hotel_amenity form-check-input" id="amenity_room_service"
								value="ROOM SERVICE" /> <label class="form-check-label"
								for="amenity_room_service">Room Service</label>
						</div>

						<input style="margin-top: 25px" class="btn btn-warning"
							type="button" id="filterBtn" value="FILTER" />
					</div>
				</div>
			</div>

			<div class="col-9">
				<div class="container rounded" id="listHotel">
					<!-- <div style='text-align: center; font-size: 20px; font-family: "Trebuchet MS", Helvetica, sans-serif'>List
					of Hotels:</div> -->

					<!-- <div class="container">
						<table class="table table-bordered table-stripped" id="tableOfHotels">
							<thead class="thead-dark" id="tableHead">
								<tr class="header">
									<th>Image</th>
									<th>Name</th>
									<th>Address</th>
									<th>Avg. Price</th>
									<th>Amenities</th>
									<th>Rating</th>
									<th>Reviews</th>
									<th>Q&A</th>
								</tr>
							</thead>
							<tbody id="tableBody">
							</tbody>
						</table>
					</div> -->
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="perTicketModal">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Search Hotel Rooms</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="container">
						<!-- class="col"> -->
						<table id="hotelRooms" class="table table-bordered table-stripped">
							<thead class="thead-dark" id="hotelRoomsHead">
								<tr class="header">
									<th>Book</th>
									<th>Image</th>
									<th>Room Type</th>
									<th>Area</th>
									<th>Price</th>
									<th>Description</th>
									<th>Policies</th>
									<th>Rooms Available</th>
									<th>Discount</th>
									<th>Amenities</th>
								</tr>
							</thead>
							<tbody id="hotelRoomsBody">
							</tbody>
						</table>
					</div>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="hotelRoomsModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Are these details correct?</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="hotelRooms_modalBody"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="bookingHotelRoomModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="bookingRoom_modalBody">
					<div class="col">
						<div>
							<input class="form-control" type="hidden" id="booking_userId"
								value="${currentUser.userId}" />
						</div>
						<div>
							<input class="form-control" type="hidden" id="booking_status"
								value="Active" />
						</div>
						<div>
							<input class="form-control" type="hidden" id="booking_hotelId" />
						</div>
						<div>
							<input class="form-control" type="hidden"
								id="booking_hotelRoomId" />
						</div>
						<div>
							<input class="form-control" type="hidden"
								id="booking_hotelRoomPrice" />
						</div>
						<div>
							Hotel Name: <input readonly="true" class="form-control"
								type="text" id="booking_hotelName" />
						</div>
						<div>
							Customer Mobile: <input class="form-control" type="text"
								id="booking_customerMobile" />
						</div>
						<div id="noGuestsDiv">
							No. Guests: <input class="form-control booking-update"
								type="number" id="booking_noGuests" />
						</div>
						<div>
							No. Rooms: <input class="form-control booking-update"
								type="number" id="booking_noRooms" />
						</div>
						<div>
							Check-In Date: <input class="form-control booking-update"
								type="date" id="booking_checkInDate" />
						</div>
						<div>
							Check-Out Date: <input class="form-control booking-update"
								type="date" id="booking_checkOutDate" />
						</div>
						<div>
							Room Type: <input readonly="true" class="form-control"
								type="text" id="booking_roomType" />
						</div>
						<div>
							Discount: <span id="booking_discount"></span>%
						</div>
						<div>
							Total Price: $<span id="booking_price"></span>
						</div>
						<div style='margin-top: 20px'>
							<button class='btn-confirm-booking btn btn-warning view-guests'>Continue</button>
						</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="guestModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="guestModal_modalBody">
					<div class="col">
						<table id="guestsTable">
							<thead>
								<tr>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Gender</th>
									<th>Age</th>
								</tr>
							</thead>
							<tbody id="guestBody">
							</tbody>
						</table>
					</div>
					<div style='margin-top: 20px'>
						<button class='btn-confirm-booking btn btn-warning save-booking'>Confirm
							Booking</button>
					</div>
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="userLoginModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="loginModal_modalBody">
					<div class="col">
						<h3>Login Below</h3>
						<form action="login" method="POST">
							<table>
								<tr>
									<td>User Name:</td>
									<td><input type="text" name="username" /></td>
								</tr>
								<tr>
									<td>Password:</td>
									<td><input type="password" name="password" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="submit"
										value="Login" class="btn btn-warning" /></td>
								</tr>
							</table>
						</form>
						<a href="user" class="btn btn-warning">Create Account</a>
					</div>
					<!-- 					<div style='margin-top: 20px'>
						<button class='btn-confirm-booking btn btn-primary save-booking'>Confirm
							Booking</button>
					</div> -->
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="bookingHistoryModal">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Bookings for ${currentUser.username}</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="historyModal_modalBody">
					<div class="col">
						<br />
						<h4>All Of Your Bookings</h4>
						<br />
						<div>
							<input class="form-control" type="hidden" id="booking_userName"
								value="${currentUser.username}" />
						</div>
						<table id="historyTable"
							class="table table-bordered table-stripped">
							<thead class="thead-dark">
								<tr class="header">
									<th>Booking ID</th>
									<th>Hotel Name</th>
									<th>Booked On</th>
									<th>Check In</th>
									<th>Check Out</th>
									<th>Nr. Of Rooms</th>
									<th>Price</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="historyBody">
							</tbody>
						</table>
					</div>
					<!-- <div style='margin-top: 20px'>
						<button class='btn-confirm-booking btn btn-primary save-booking'>Confirm
							Booking</button>
					</div> -->
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="hotelReviewModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Write a review about your trip!</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="hotelReview_modalBody">
					<div class="col">
						<form id="reviewForm">
							<div>
								<input class="form-control" type="hidden"
									id="hotelReview_hotelId" />
							</div>
							<div>
								<input class="form-control" type="hidden"
									id="hotelReview_userName" />
							</div>
							<div>
								Today's Date: <input class="form-control" type="date"
									id="hotelReview_reviewDate" />
							</div>
							<div>
								Rate your room: <input type="range" min="1" max="5" value="3"
									class="form-control-range" id="hotelReview_roomRating">
							</div>
							<div>
								Rate food: <input type="range" min="1" max="5" value="3"
									class="form-control-range" id="hotelReview_foodRating">
							</div>
							<div>
								Rate cleanliness: <input type="range" min="1" max="5" value="3"
									class="form-control-range" id="hotelReview_cleanlinessRating">
							</div>
							<div>
								Rate overall value: <input type="range" min="1" max="5"
									value="3" class="form-control-range"
									id="hotelReview_valueRating">
							</div>
							<div>
								Rate service: <input type="range" min="1" max="5" value="3"
									class="form-control-range" id="hotelReview_serviceRating">
							</div>
							<div class="form-group">
								<label for="hotelReview_description">Additional
									Comments:</label>
								<textarea class="form-control" rows="4"
									id="hotelReview_description"></textarea>
							</div>
							<div style='margin-top: 20px'>
								<button class='btn btn-warning submit-review'>Submit</button>
							</div>
						</form>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="closeReviewModal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="hotelReviews">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Reviews</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="historyModal_modalBody">
					<div class="col">
						<br />
						<h4>Recent Reviews</h4>
						<br />
						<table id="reviewsTable"
							class="table table-bordered table-stripped">
							<thead class="thead-dark">
								<tr class="header">
									<th>Posted On</th>
									<th>User</th>
									<th>Hotel Name</th>
									<th>Avg. Rating</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody id="reviewsBody">
							</tbody>
						</table>
					</div>
					<!-- <div style='margin-top: 20px'>
						<button class='btn-confirm-booking btn btn-primary save-booking'>Confirm
							Booking</button>
					</div> -->
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="hotelQAModal">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Questions & Answers</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="QAModal_modalBody">
					<div id="qaBody"></div>
					<div id="qaFormbody"></div>
				</div>


				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<script>
		var slider = document.getElementById("priceRange");
		var output = document.getElementById("priceValue");
		output.innerHTML = slider.value;
		slider.oninput = function() {
			output.innerHTML = this.value;
		}
	</script>
</body>
</html>