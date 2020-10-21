<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>User Management</title>
</head>
<body>
	<div class="container p-3 my-3" align="center">
		<h1>Create A New Account</h1>
		<nav
			class="navbar navbar-expand-sm bg-primary navbar-light justify-content-center">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="home">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="login">Login</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<h2>User Management</h2>
		<frm:form action="saveUser" method="POST" modelAttribute="user"
			name="userForm">
			<table>
				<tr>
					<td>Username:</td>
					<td><frm:input path="username" /></td>
				</tr>

				<tr>
					<td>Password:</td>
					<td><frm:input type="password" path="password" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><frm:input path="email" /></td>
				</tr>
				<tr>
					<td>Phone Number:</td>
					<td><frm:input path="phoneNumber" /></td>
				</tr>
				<sec:authorize access="hasAuthority('Administrator') || hasAuthority('Manager')">
					<tr>
						<td>Roles:</td>
						<td><c:forEach items="${roleSet}" var="role">
								<c:choose>
									<c:when test="${fn:contains(selectedRoles, role)}">
										<strong>${role.roleName}</strong>
										<frm:checkbox path="roles" value="${role.roleId}"
											checked="true" />&nbsp;&nbsp;
										</c:when>
									<c:otherwise>
											${role.roleName}<frm:checkbox path="roles"
											value="${role.roleId}" />&nbsp;&nbsp;
										</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</tr>
				</sec:authorize>
				<tr>
					<td><input type="submit" value="Create"
						class="btn btn-primary" /></td>
				</tr>
			</table>
		</frm:form>
	</div>
</body>
</html>