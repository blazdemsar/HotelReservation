$( document ).ready(function() {
	var searchMicroservice = "http://localhost:8383";
	loadHotel();
	function loadHotel(){
		$('#listHotel tr td').parents('tr').remove();
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : searchMicroservice + "/getAllHotels",
			success : function(result) {
				 $.each(result, function(key1, value1) {
					 $("#listHotel").append("<tr><td>"+ value1.hotelName + " </td><td>"+ value1.hotelStar +"</td><td>" + value1.hotelAddress +"</td></tr>");
				});
				
				console.log(result);
				
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});

	}
	
    $("#searchBtn").click(function(event) {
    	$('#listHotel tr td').parents('tr').remove();
    	var searchDetails = {
    			"searchHotel" : $("#searchHotel").val(),
    			"checkIn" :  $("#checkIn").val(),
    			"checkOut" :  $("#checkOut").val(),
    			"noOfRooms" :  $("#noOfRooms").val(),
    			"noOfGuests" :  $("#noOfGuests").val()
    			
        }
    	console.log(searchDetails);
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : searchMicroservice + "/getHotels",
			data : JSON.stringify(searchDetails),
			dataType : 'json',
			success : function(result) {
				 $.each(result, function(key1, value1) {
					 $("#listHotel").append("<tr><td>"+ value1.hotelName + " </td><td>"+ value1.hotelStar +"</td><td>" + value1.hotelAddress +"</td></tr>");
				});
				
				console.log(result);
				
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});

	});
   
})