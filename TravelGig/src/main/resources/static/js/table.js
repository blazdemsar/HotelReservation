$(document).ready(function() {
	var apiGatewayBaseUri = 'http://localhost:8282/';
	$('#desigTable').dataTable({
		paginate : true,
		"order" : [ [ 1, 'asc' ] ]
	});

	$('#empDetails').hide();
	$("#desigTable").on("click", ".seeDetails", function() {

		var empName = $(this).parent().parent().children("td").eq(0).text();
		var empAge = $(this).parent().parent().children("td").eq(1).text();
		var empGender = $(this).parent().parent().children("td").eq(2).text();
		var empId = $(this).attr("data-empcode");

		$("#empId").val(empId);
		$("#empName").val(empName);
		$("#empAge").val(empAge);
		$("#empGender").val(empGender);

		$('#empDetails').modal('toggle');
	});

	$("#editEmp").submit(function() {
		
		var emp = {
			"empId" : $("#empId").val(),
			"empName" : $("#empName").val(),
			"empAge" : $("#empAge").val(),
			"empGender" : $("#empGender").val()
		}

		$('#empDetails').modal('toggle');
		var uri = apiGatewayBaseUri + "editEmp";

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : uri,
			dataType : "json",
			data : JSON.stringify(emp),
			success : function(result) {
				alert(result.success);
				console.log(result);
			},
			error : function(e) {
				alert("Error!");
				console.log("ERROR: ", e);
			}
		})
		return false;
	});

});