<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<script src="/js/jquery-2.1.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#country").change(function(){
		alert($(this).val());
	});
});

</script>
<meta charset="ISO-8859-1">
<title>Add Hotel</title>
</head>
<body>
	<frm:form action="addHotel" method="POST" modelAttribute="hotel">
		<table>
			<tr>
				<td>Customer ID:</td>
				<td><frm:input path="customerId" /></td>
				<td><frm:errors path="customerId" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Full Name:</td>
				<td><frm:input path="customerName" /></td>
				<td><frm:errors path="customerName" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><frm:radiobutton name="customerGender"
						path="customerGender" value="Male" label="Male" /> <frm:radiobutton
						name="customerGender" path="customerGender" value="Female"
						label="Female" /></td>
				<td><frm:errors path="customerGender" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Date of Birth:</td>
				<td><frm:input type="date" path="customerDob" /></td>
				<td><frm:errors path="customerDob" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Mobile Number:</td>
				<td><frm:input path="customerMobileNo" /></td>
				<td><frm:errors path="customerMobileNo" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><frm:input path="customerPhone" /></td>
				<td><frm:errors path="customerPhone" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><frm:input path="customerEmail" /></td>
				<td><frm:errors path="customerEmail" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Address Line 1:</td>
				<td><frm:input path="customerAddress.addressLine1" /></td>
				<td><frm:errors path="customerAddress.addressLine1"
						cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>Address Line 2:</td>
				<td><frm:input path="customerAddress.addressLine2" /></td>
				<td><frm:errors path="customerAddress.addressLine2"
						cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><frm:input path="customerAddress.city" /></td>
				<td><frm:errors path="customerAddress.city"
						cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><frm:input path="customerAddress.state" /></td>
				<td><frm:errors path="customerAddress.state"
						cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>SSN:</td>
				<td><frm:input path="ssn" /></td>
				<td><frm:errors path="ssn" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td>User ID:</td>
				<td><frm:input path="user.userId" /></td>
				<td><frm:errors path="user.userId" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Create"
					class="btn btn-danger" /></td>
			</tr>
		</table>
	</frm:form>
</body>
</html>