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
</head>
<body>
	<sec:authorize access="hasAuthority('Administrator') || hasAuthority('Manager')">
		<div class="container">
			<h1>Role Management</h1>
			<frm:form action="saveRole" method="POST" modelAttribute="role">
				<table>
					<tr>
						<td>Role ID:</td>
						<td><frm:input path="roleId" /></td>
					</tr>
					<tr>
						<td>Role Name:</td>
						<td><frm:input path="roleName" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="Submit"
							value="Submit" /></td>
					</tr>
				</table>
			</frm:form>
			<br />
			<h3>Existing Roles</h3>
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th align="center">Role ID</th>
						<th align="center">Role Name</th>
						<th align="center" colspan="2">Action</th>
					</tr>
				</thead>
				<c:forEach items="${roles}" var="r">
					<tr>
						<td align="center">${r.roleId}</td>
						<td align="center">${r.roleName}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</sec:authorize>
</body>
</html>