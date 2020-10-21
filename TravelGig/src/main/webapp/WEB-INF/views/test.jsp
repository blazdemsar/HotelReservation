<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<script src="js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script>
$("document").ready(function() {
	$("#change").click(function() {
		$("h3").text("Testing changing text in h3");
		$("li:odd").hide();
	});
});
$("document").ready(function() {
	$("#bringBack").click(function() {
		$("h3").text("We are learning jQuery");
		$("li:odd").show();
		alert("Changes have been restored.");
	});
	$("#50px").click(function(){
		$("h3").css({"font-size":"300%","background-color":"yellow"});				
	});
});
</script>
</head>
<body>
<div class="container border rounded" style="margin:auto;padding:50px;margin-top:50px;margin-bottom:50px">
	<h3>We are learning jQuery</h3>
	<p>Programming Languages</p>
	<ul>
		<li id="50px">Java</li>
		<li>Python</li>
		<li>jQuery</li>
		<li>React</li>
	</ul>
	<button id="change">Change</button>
	<button id="bringBack">Revert</button>
</div>
</body>
</html>