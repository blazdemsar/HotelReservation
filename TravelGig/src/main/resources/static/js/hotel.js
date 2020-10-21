$(document).ready(function() {
		
		var hotels = {
			"hotelKey" : ""
		};
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getHotelName",
			dataType : "json",
			data : JSON.stringify(hotels),
			cache : false,
			success : function(result) {

				var hotelArr = new Array();
				var count = 0;

				$.each(result.listHotel, function(key, value) {
								
					//hotelArr.push(value);//.hotelName);

					var hotelAmenities = [...value.amenities].map(a => a.name).join(", ");
								

					$("#listHotel").append(
						"<div class='row border rounded' style='margin: auto; margin: 10px 0; padding: 16px;'>"+
							"<div class='col-3' style='display: flex; justify-content: center; align-items: center;' align='center'>"+
								"<div class='container rounded' style='vertical-align: middle;' align='center; height: 200px; width: 300px; background-size: auto;'>"+
									"<a href='#' class='view-hotel' style='display:block' data-hotelId='" + value.hotelId + "' data-hotelName='"+ value.hotelName+"'>"+
										"<img style='max-width: 100%; max-height: 100%; display: block;' src='"+value.imageUrl+"'/>"+
									"</a>"+
								"</div>"+
							"</div>"+
							"<div class='col-6'>"+
								"<div class='container' id='hotelProps'>"+
									"<h5>"+value.hotelName+"</h4>"+
									"<p style='font-size: 11px;' id='hotelAddress'>"+value.hotelAddress.addressLine1+", "+value.hotelAddress.city+", "+value.hotelAddress.state+""+
									"<p style='display: none' id='hotelName'>"+value.hotelName+"</p>"+
									"<p style='display: none' id='avgPrice'>"+value.avgPrice+"</p>"+
									"<p style='display: none' id='hotelAmenities'>"+hotelAmenities+"</p>"+
									"<p style='display: none' id='hotelStar'>"+value.star+"</p>"+
									"<p id='hotelDescription'>"+value.description+"</p>"+
								"</div>"+
							"</div>"+
							"<div class='col-3' style='display: flex; justify-content: center; align-items: center;' align='center'>"+
								"<div class='container' style='vertical-align: middle;' align='center'>"+
									"<span><a href='#' style='vertical-align: middle;' class='btn btn-warning view-reviews' data-reviewHotelId='"+ value.hotelId +"' data-reviewHotelName='" + value.hotelName + "'>Reviews</a></span>"+
									"<br/><br/>"+
									"<span><a href='#' style='vertical-align: middle;' class='btn btn-warning view-questionAnswer' data-qaHotelId='"+ value.hotelId +"'>Q&A</a></span>"+
								"</div>"+
							"</div>"+
						"</div>"
					);
					count++;
				});
				console.log(hotelArr);
			},
			error : function(e) {
				alert("Error!");
				console.log("ERROR: ", e);
			}
		});
		
		$(document).on('click', '.submit-qa', function() {

			var hotelId = $("#qa_hotelId").val();
			var question = $("#qa_question").val();
			
			var qaDetails = {
				"hotelId" : hotelId,
				"question" : question
			}

			$.ajax({
				type : "POST",
				contentType : "application/json", //type of date being sent
				url : "saveQuestionAnswer",
				dataType : "json", // type of data expected to receive
				data : JSON.stringify(qaDetails),
				cache : false,
				success : function(result) {

					$("#hotelQAModal").modal('toggle');
					$("#qaForm").trigger("reset");
					
				},
				error : function(e) {
					alert("Error fetching Q&A!");
					console.log("ERROR: ", e);
				}
			})
			
		})
		
		//hotelQ&A
		$(document).on('click', '.view-questionAnswer', function() {

			$("#qaBody").children().remove();
			$("#qaForm").trigger("reset");
			$("#qaFormbody").children().remove();
			
			var hotelId = $(this).attr("data-qaHotelId");

			var qaDetails = {
				"hotelId" : hotelId
			}

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getHotelQA",
				dataType : "json",
				data : JSON.stringify(qaDetails),
				cache : false,
				success : function(result) {

					$.each(result.listOfHotelQA, function(key, value) {
						
						$("#qaBody").append(
							"<div class='container'>"+
							"<strong>"+value.question+"</strong><br/>"+
							"<p>"+value.answer+"</p>"+
							"</div>"
						)
					})

					$("#qaFormbody").append(
						"<div class='container'>"+
						"<strong>Don't see your question? Ask us below!</strong>"+
						"<form id='qaForm' >"+
						"<div><input class='form-control' type='hidden' id='qa_hotelId' value='"+hotelId+"'/></div>"+
						"<div class='form-group'>"+
	  					"<label for='qa_question'>What would you like to know?</label>"+
	  					"<textarea class='form-control' rows='4' id='qa_question'></textarea>"+
						"</div>"+ 
						"<div style='margin-top: 20px'>"+
						"<button class='btn btn-warning submit-qa'>Submit</button>"+
						"</div>"+
						"</form>"+
						"</div>"
					)
					
				},
				error : function(e) {
					alert("Error fetching Q&A!");
					console.log("ERROR: ", e);
				}
			})

			$("#hotelQAModal").modal('toggle');

		})
		
		$(document).on('click', '#closeReviewModal', function() {
			$("#reviewForm").trigger("reset");
		})
		
		$(document).on('click', '.view-reviews', function() {

			$("#reviewsBody").children().remove();
			
			var hotelId = $(this).attr("data-reviewHotelId");
			var hotelName = $(this).attr("data-reviewHotelName");

			var reviewDetails = {
				"hotelId" : hotelId
			}

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getReviewsForHotel",
				dataType : "json",
				data : JSON.stringify(reviewDetails),
				cache : false,
				success : function(result) {

					$.each(result.listOfReviews, function(key, value) {

						var cleanlinessRat = value.cleanlinessRating;
						var foodRat = value.foodRating;
						var roomRat = value.roomRating;
						var serviceRat = value.serviceRating;
						var valRat = value.valueRating;

						var avgRating = (cleanlinessRat + foodRat + roomRat + serviceRat + valRat) / 5;
						
						$("#reviewsBody").append(
								"<tr>"+
								"<td>"+value.reviewDate+"</td>" +
								"<td>"+value.userName+"</td>"+
								"<td>"+hotelName+"</td>"+
								"<td>"+avgRating+"</td>"+
								"<td>"+value.description+"</td>"+
								"</tr>"
						)
					})
					
				},
				error : function(e) {
					alert("Error fetching reviews!");
					console.log("ERROR: ", e);
				}
			})

			$("#hotelReviews").modal('toggle');
			//$("#hotelReviewModal").empty();
			
		})
		
		$(document).on('click', '.submit-review', function() {

			var hotelReview = {
					"hotelId" : $("#hotelReview_hotelId").val(),
					"userName" : $("#hotelReview_userName").val(),
					"reviewDate" : $("#hotelReview_reviewDate").val(),
					"roomRating" : $("#hotelReview_roomRating").val(),
					"foodRating" : $("#hotelReview_foodRating").val(),
					"cleanlinessRating" : $("#hotelReview_cleanlinessRating").val(),
					"valueRating" : $("#hotelReview_valueRating").val(),
					"serviceRating" : $("#hotelReview_serviceRating").val(),
					"description" : $("#hotelReview_description").val(),
			}



			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "saveReview",
				dataType : "json",
				data : JSON.stringify(hotelReview),
				cache : false,
				success : function(result) {

					$("#hotelReviewModal").modal('toggle');
					$("#reviewForm").trigger("reset");
					
				},
				error : function(e) {
					alert("Error saving a review!");
					console.log("ERROR: ", e);
				}
			})
		})
		
		$(document).on('click', '.write_review', function() {

			var hotelId = $(this).attr("data-hotelId");
			var username = $(this).attr("data-username");

			$("#hotelReview_hotelId").val(hotelId);
			$("#hotelReview_userName").val(username);

			//alert(hotelId);
			//alert(username);

			$("#hotelReviewModal").modal('toggle');
		})
		
		$(document).on('click', '.cancel_booking', function() {
			var currentRow = $(this).parent().parent();
			var bookingId = $(this).attr("data-bookingId");

			var bookingDetails = {
				"bookingId" : bookingId
			}

			//alert(bookingId);

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "updateBookingStatus",
				dataType : "json",
				data : JSON.stringify(bookingDetails),
				cache : false,
				success : function(result) {
					var value = result.booking;

					$(currentRow).children("td").eq("7").text(value.status);
					
				},
				error : function(e) {
					alert("Error updating a booking!");
					console.log("ERROR: ", e);
				}
			})

			return false; // to prevent hyperlink from redirecting
		})
		
		$(document).on('click', '#booking_history', function () {

			var username = $("#booking_userName").val();
			//alert(username);
			$("#historyBody").children().remove();
			
			$.ajax({
				type : "POST",
				//contentType : "application/json",
				url : "getBookingsByUserId",
				dataType : "json",
				//data : JSON.stringify(bookingDetails),
				cache : false,
				success : function(result) {
					
					$.each(result.listOfBookings, function(key, value) {

						var adaptStatus = "";
						var adaptReview = "";
						var d = new Date();
						var month = d.getMonth()+1;
						var date = d.getDate();
						var today = d.getFullYear() + (month<10?'0':'') + month + (date<10?'0':'') + date;
						var checkInDate = value.checkInDate.replaceAll("-", "");

						if (value.status == "Canceled") {
							adaptStatus = "Canceled";
							adaptReview = "N/A";
						} else if (checkInDate < today) {
							adaptStatus = "Completed";
							adaptReview = "<a href='#' class='btn btn-primary write_review' data-hotelId='"+value.hotelId+"' data-username='"+username+"'>Write A Review</a>";
						} else {
							adaptStatus = "Active<br/><a href='#' class='btn btn-primary cancel_booking' data-bookingId='"+value.bookingId+"'>Cancel</a>";
							adaptReview = "<a href='#' class='btn btn-primary write_review disabled' data-hotelId='"+value.hotelId+"' data-username='"+username+"'>Write A Review</a>";
						}
						
						$("#historyBody").append(
							"<tr>"+
							"<td>"+value.bookingId+"</td>" +
							"<td>"+value.hotelName+"</td>"+
							"<td>"+value.bookedOnDate+"</td>"+
							"<td>"+value.checkInDate+"</td>"+
							"<td>"+value.checkOutDate+"</td>"+
							"<td>"+value.noRooms+"</td>"+
							"<td>$"+value.price+"</td>"+
							"<td>"+adaptStatus+"</td>"+
							"<td>"+adaptReview+"</td>"+
							"</tr>"
						)
						
					})
					
				},
				error : function(e) {
					alert("Error saving a booking!");
					console.log("ERROR: ", e);
				}
			})
			
			$("#bookingHistoryModal").modal('toggle');
			
		})

		$(document).on('click', '.view-guests', function () {

			var noGuests = parseInt($("#booking_noGuests").val());
			//alert(noGuests);

			for (i = 0; i < noGuests; i++) {
				$("#guestBody").append(
					"<tr>"+
					"<td><input class='form-control' type='text' name='firstName' /></td>"+
					"<td><input class='form-control' type='text' name='lastName' /></td>"+
					"<td><input class='form-control' type='text' name='gender' /></td>"+
					"<td><input class='form-control' type='number' name='age' /></td>"+
					"</tr>"
				)
			}
			
			$("#bookingHotelRoomModal").modal('toggle');
			$("#guestModal").modal('toggle');
		})
		
		$(document).on('click', '.save-booking', function() {

			var guests = [];

			$.each($("#guestBody").children("tr"), function () {

				var guest = {};
				$(this).find("input").each(function () {
					//alert("name: " + $(this).attr('name') + ", value: " + $(this).val())
					guest[$(this).attr('name')] = $(this).val();
					
				})
				guests.push(guest);
			})

			//alert("guests: " + JSON.stringify(guests));
			var date = new Date();
			var today = (date.getMonth()+1) + "/" + date.getDate() + "/" + date.getFullYear();
			
			var bookingDetails = {
				"hotelId" : $("#booking_hotelId").val(),
				"hotelRoomId" : $("#booking_hotelRoomId").val(),
				"noRooms" : $("#booking_noRooms").val(),
				"guests" : guests,
				"checkInDate" : $("#booking_checkInDate").val(),
				"checkOutDate" : $("#booking_checkOutDate").val(),
				"bookedOnDate" : today,
				"status" : $("#booking_status").val(),
				"price" : $("#booking_price").text(),
				"discount" : $("#booking_discount").text(),
				"customerMobile" : $("#booking_customerMobile").val(),
				"roomType" : $("#booking_roomType").val(),
				"hotelName" : $("#booking_hotelName").val(),
				"userId" : $("#booking_userId").val()
			}
			

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "saveBooking",
				dataType : "json",
				data : JSON.stringify(bookingDetails),
				cache : false,
				success : function(result) {

				},
				error : function(e) {
					alert("Error saving a booking!");
					console.log("ERROR: ", e);
				}
			})

			
		})
			
		
		// view-hotel-link
		$(document).on('click', '.view-hotel', function () {

			var hotelId = $(this).attr("data-hotelId");
			var hotelName = $(this).attr("data-hotelName");
			var checkInDate = $("#checkInDate").val();
			var checkOutDate = $("#checkOutDate").val();
			var noRooms = $("#noRooms").val();
			
			var checkIn = new Date();
			var date = new Date();
			var time = date.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000));
			var checkOut = new Date(time);
			
			//alert(checkIn);
			//alert(checkOut);
			
			if (checkInDate == null || checkInDate == "") {
				
				checkInDate = "" + checkIn.getFullYear();
				
				if (checkIn.getMonth() < 10) {
					checkInDate = checkInDate + "-0" + checkIn.getMonth();
				} else {
					checkInDate = checkInDate + checkIn.getMonth();
				}
				
				if (checkIn.getDate() < 10) {
					checkInDate = checkInDate + "-0" + checkIn.getDate();
				} else {
					checkInDate = checkInDate + "-" + checkIn.getDate();
				}
				
				//alert(checkInDate);
			}
			
			if (checkOutDate == null || checkOutDate == "") {
				
				checkOutDate = "" + checkOut.getFullYear();
				
				if (checkOut.getMonth() < 10) {
					checkOutDate = checkOutDate + "-0" + checkOut.getMonth();
				} else {
					checkOutDate = checkOutDate + checkOut.getMonth();
				}
				
				if (checkOut.getDate() < 10) {
					checkOutDate = checkOutDate + "-0" + checkOut.getDate();
				} else {
					checkOutDate = checkOutDate + "-" + checkOut.getDate();
				}
				
				//alert(checkOutDate);
			}
			
			if (noRooms == null || noRooms == "") {
				noRooms = 1;
			}

			//alert(hotelName);
			
			var hotelDetail = {
				"hotelId" : hotelId,
				"checkInDate" : checkInDate,
				"checkOutDate" : checkOutDate,
				"noRooms" : noRooms
			}

			$("#hotelRoomsBody").children().remove();
			// use the code below for modal later
			//$("#perTicketModal").modal('toggle');

			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getHotelRoomsAvailable",
				dataType : "json",
				data : JSON.stringify(hotelDetail),
				cache : false,
				success : function(result) {

					var hotelRoomsArray = new Array();
					var count = 0;
					
					$.each(result.listHotelRoomsAvailable, function(key, value) {

						//hotelRoomsArray.push(value);

						var hotelRoomAmenities = [...value.amenities].map(a => a.name).join(", ");
						
						$("#hotelRoomsBody").append(
							"<tr>"+
							"<td><a href='#' class='view-hotelRoom' data-roomId='" + value.roomId + "' data-roomType='"+value.type.name+"' data-price='"+value.price+"' data-discount='"+value.discount+"' data-hotelId='"+hotelId+"' data-hotelName='"+hotelName+"'>Book Now</a></td>" +
							"<td><img style='width:150px; height:100px;' src='"+value.imageUrl+"'/></td>"+
							"<td>"+value.type.name+"</td>"+
							"<td>"+value.area+"</td>"+
							"<td>"+value.price+"</td>"+
							"<td>"+value.description+"</td>"+
							"<td>"+value.policy+"</td>"+
							"<td>"+value.totalroom+"</td>"+
							"<td>"+value.discount+"%</td>"+
							"<td>"+hotelRoomAmenities+"</td>"+
							"</tr>"
						)
						
					})
				}
			})

			$("#perTicketModal").modal('toggle');
			
		});


		$(document).on('click', '.view-hotelRoom', function () {

			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "validateBooking",
				cache : false,
				success : function(result) {

				},
				error : function(e) {
					if (e.status==401) {
						window.location.href="login"
					}
				}
			})
			
			var hotelId = $(this).attr("data-hotelId");
			var roomId = $(this).attr("data-roomId");
			var hotelName = $(this).attr("data-hotelName");
			var noRooms = $("#noRooms").val();
			var noGuests = $("#noGuests").val();
			var checkInDate = $("#checkInDate").val();
			var checkOutDate = $("#checkOutDate").val();
			var roomType = $(this).attr("data-roomType");
			var discount = $(this).attr("data-discount");
			var price =  $(this).attr("data-price");
			var checkIn = new Date(checkInDate);
			var checkOut = new Date(checkOutDate);
			var nrOfNights = (checkOut.getTime() - checkIn.getTime()) / (1000 * 3600* 24);

			var totalPrice = price * noRooms * noGuests * nrOfNights * (1 - (discount/100));

			$("#booking_hotelId").val(hotelId);
			$("#booking_hotelRoomId").val(roomId);
			$("#booking_hotelRoomPrice").val(price);
			$("#booking_hotelName").val(hotelName);
			$("#booking_noRooms").val(noRooms);
			$("#booking_noGuests").val(noGuests);
			$("#booking_checkInDate").val(checkInDate);
			$("#booking_checkOutDate").val(checkOutDate);
			$("#booking_roomType").val(roomType);
			$("#booking_discount").text(discount);
			$("#booking_price").text(totalPrice);

			$("#perTicketModal").modal('toggle');
			$("#bookingHotelRoomModal").modal('toggle');

		});

		$(".booking-update").blur(function () {

			console.log("Blur event triggered");
			
			var noRooms = $("#booking_noRooms").val();
			var noGuests = $("#booking_noGuests").val();
			var checkInDate = $("#booking_checkInDate").val();
			var checkOutDate = $("#booking_checkOutDate").val();
			var checkIn = new Date(checkInDate);
			var checkOut = new Date(checkOutDate);
			var nrOfNights = (checkOut.getTime() - checkIn.getTime()) / (1000 * 3600* 24);
			var price = $("#booking_hotelRoomPrice").val();
			var discount = parseFloat($("#booking_discount").text());
			var totalPrice = price * noRooms * noGuests * nrOfNights * (1 - (discount/100));

			//$("#booking_noRooms").val(noRooms);
			//$("#booking_noGuests").val(noGuests);
			//$("#booking_checkInDate").val(checkInDate);
			//$("#booking_checkOutDate").val(checkOutDate);
			$("#booking_price").text(totalPrice);
		});
		
		
		$("#searchBtn").click(function() {
			
			var hotelKey = $("#searchLocation").val();
			var hotelDetails = {
				"hotelKey" : hotelKey
			};

			$("#tableBody").children().remove();
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getHotelName",
				dataType : "json",
				data : JSON.stringify(hotelDetails),
				cache : false,
				success : function(result) {

					var hotelArr = new Array();
					var count = 0;

					$.each(result.listHotel, function(key, value) {
								
						//hotelArr.push(value);//.hotelName);

						var hotelAmenities = [...value.amenities].map(a => a.name).join(", ");
								
						$("#listHotel").append(
						"<div class='row border rounded' style='margin: auto; margin: 10px 0; padding: 16px;'>"+
							"<div class='col-3' style='display: flex; justify-content: center; align-items: center;' align='center'>"+
								"<div class='container rounded' style='vertical-align: middle;' align='center; height: 200px; width: 300px; background-size: auto;'>"+
									"<a href='#' class='view-hotel' style='display:block' data-hotelId='" + value.hotelId + "' data-hotelName='"+ value.hotelName+"'>"+
										"<img style='max-width: 100%; max-height: 100%; display: block;' src='"+value.imageUrl+"'/>"+
									"</a>"+
								"</div>"+
							"</div>"+
							"<div class='col-6'>"+
								"<div class='container' id='hotelProps'>"+
									"<h5>"+value.hotelName+"</h4>"+
									"<p style='font-size: 11px;' id='hotelAddress'>"+value.hotelAddress.addressLine1+", "+value.hotelAddress.city+", "+value.hotelAddress.state+""+
									"<p style='display: none' id='hotelName'>"+value.hotelName+"</p>"+
									"<p style='display: none' id='avgPrice'>"+value.avgPrice+"</p>"+
									"<p style='display: none' id='hotelAmenities'>"+hotelAmenities+"</p>"+
									"<p style='display: none' id='hotelStar'>"+value.star+"</p>"+
									"<p id='hotelDescription'>"+value.description+"</p>"+
								"</div>"+
							"</div>"+
							"<div class='col-3' style='display: flex; justify-content: center; align-items: center;' align='center'>"+
								"<div class='container' style='vertical-align: middle;' align='center'>"+
									"<a href='#' style='vertical-align: middle;' class='btn btn-warning view-reviews' data-reviewHotelId='"+ value.hotelId +"' data-reviewHotelName='" + value.hotelName + "'>Reviews</a>"+
									"<br/><br/>"+
									"<a href='#' style='vertical-align: middle;' class='btn btn-warning view-questionAnswer' data-qaHotelId='"+ value.hotelId +"'>Q&A</a>"+
								"</div>"+
							"</div>"+
						"</div>"
					);
						count++;
					});
					console.log(hotelArr);
				},
				error : function(e) {
					alert("Error!");
					console.log("ERROR: ", e);
				}
			});
		});

		$("#filterBtn").click(function() {

			
			$("#listHotel").children().show();
			var selectedRatings = $(".star_rating:checked");
			var ratingsLength = selectedRatings.length>0?true:false;
			//alert(ratingsLength);

			$.each($("#listHotel").children("div"), function () {


				//================== Filter by star rating ==================
				
				var hotelName = $(this).find("#hotelName").text();
				//alert("hotelName: " + hotelName);
				
				var hotelStar = $(this).find("#hotelStar").text(); //$(this).children("p").eq(4).text();
				//alert(hotelStar);
				var starFlag = 0;
				
				$.each(selectedRatings, function() {

					var selectedRating;
					
					if ($(this).prop("checked")) {

						selectedRating  = $(this).val();
						
					}

					if (hotelStar == selectedRating) {
						starFlag = 1;
					}
				})

				if (starFlag == 0 && ratingsLength) {
					$(this).hide();
				}

				//================== END STAR RATING FILTER ==================

				//================== Filter by price ==================

				var hotelPrice = parseFloat($(this).find("#avgPrice").text());
				//alert("hotelPrice: " + hotelPrice);
				var selectedPriceRange = parseFloat($("#priceValue").text());
				//alert("selectedPriceRange: " + selectedPriceRange);
				var priceFlag = 0;

				if (hotelPrice > selectedPriceRange) {
					$(this).hide();
				}

				//================== END PRICE FILTER ==================

				//================== Filter by amenities ==================

				var selectedAmenities = $(".hotel_amenity:checked");
				var filterByAmenities = selectedAmenities.length>0?true:false;

				if (filterByAmenities) {

					var amenityFlag = 0;
					var included = 0;
					var hotelAmenities = $(this).find("#hotelAmenities").text().toUpperCase();
					

					$.each(selectedAmenities, function () {

						//alert(hotelAmenities + ", " + $(this).val());
						
						if (hotelAmenities.includes($(this).val())) {
							included = 1;
						} else {
							included = 0;
						}

						amenityFlag = included;
						
					})

					if (amenityFlag == 0) {
						$(this).hide();
					}
					
				}

			});
			
			//alert("Accept Filter");
		})
	});