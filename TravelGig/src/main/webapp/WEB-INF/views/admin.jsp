<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Role Management</title>
<link href="css/jquery-ui.min.css" rel="stylesheet">
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" type="text/css" href="home.css"> -->
<script>

$(document).ready(function () {

	$.ajax({
		type : "POST",
		url : "getAllQAs",
		dataType : "json",
		cache : false,
		success : function(result) {

			$.each(result.listOfAllQAs, function(key, value) {
				
				$("#qaBody").append(
					"<tr>"+
					"<td>"+value.qaId+"</td>"+
					"<td>"+value.hotelId+"</td>"+
					"<td>"+value.question+"</td>"+
					"<td>"+value.answer+"</td>"+
					"<td><a href='#' class='view-adminQA' data-qaId='"+value.qaId+"' data-hotelId='"+value.hotelId+"' data-question='"+value.question+"'>Reply</a></td>"+
					"</tr>"
				)
			})	
			
		},
		error : function(e) {
			alert("Error fetching Q&A!");
			console.log("ERROR: ", e);
		}
	})

	$(document).on('click', '.view-adminQA', function () {

		$("#qaForm").trigger("reset");
		$("#referToQuestion").children().remove();
		
		var qaId = $(this).attr("data-qaId");
		var hotelId = $(this).attr("data-hotelId");
		var question = $(this).attr("data-question");
		console.log(qaId);
		console.log(hotelId);
		console.log(question);

		$("#referToQuestion").append(
			"<strong>"+question+"</strong>"
		)

		$("#questionAnswer_qaId").val(qaId);
		$("#questionAnswer_hotelId").val(hotelId);
		$("#questionAnswer_question").val(question);

		$("#adminQAModal").modal('toggle');
	})

	$(document).on('click', '.submit-answer', function () {

		var qaId = $("#questionAnswer_qaId").val();
		var hotelId = $("#questionAnswer_hotelId").val();
		var question = $("#questionAnswer_question").val();
		var answer = $("#questionAnswer_answer").val();

		
		var qaDetails = {
			"qaId" : qaId,
			"hotelId" : hotelId,
			"question" : question,
			"answer" : answer
		}

		console.log(qaDetails);

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "updateQA",
			dataType : "json",
			data : JSON.stringify(qaDetails),
			cache : false,
			success : function(result) {

				
				
			},
			error : function(e) {
				alert("Error saving Q&A!");
				console.log("ERROR: ", e);
			}
		})
		
	})
	
})
</script>
</head>
<body>
	<sec:authorize access="hasAuthority('Administrator') || hasAuthority('Manager')">
		<div class="container p-3 my-3" align="center">
		<h1>Admin - Q&A</h1>
		<nav
			class="navbar navbar-expand-sm bg-primary navbar-light justify-content-center">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="hotel">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="role">Role</a></li>
				<li class="nav-item"><a class="nav-link active" href="admin">Admin</a></li>
				<li class="nav-item"><a class="nav-link" href="login?logout">Logout</a></li>
			</ul>
		</nav>
		</div>
		<div class="container">
			<table class="table table-bordered table-stripped" id="qaTable">
				<thead class="thead-dark">
						<tr class="header">
						<th>QA ID</th>
						<th>Hotel ID</th>
						<th>Question</th>
						<th>Answer</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody id="qaBody">
				</tbody>
			</table>
		</div>
		
		<div class="modal" id="adminQAModal">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h3 class="modal-title">Questions & Answers</h3>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body" id="adminQAModal_modalBody">
						<form id="qaForm">
							<div>
								<input class="form-control" type="hidden" id="questionAnswer_qaId"/>
							</div>
							<div>
								<input class="form-control" type="hidden" id="questionAnswer_hotelId"/>
							</div>
							<div>
								<input class="form-control" type="hidden" id="questionAnswer_question"/>
							</div>
							<div class="form-group">
	  							<label id="referToQuestion"></label>
	  							<textarea class="form-control" rows="4" id="questionAnswer_answer"></textarea>
							</div>
							<div style='margin-top: 20px'>
							<button class="btn btn-primary submit-answer">Submit</button>
						</div>
						</form>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>
</body>
</html>