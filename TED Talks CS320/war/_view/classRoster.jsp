<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>
<link rel="stylesheet" type="text/css"
	href="CSS/classRoster.css?<?php echo date('l jS \of F Y h:i:s A'); ?>" />
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Class Roster</title>

<form action="${pageContext.servletContext.contextPath}/landingPage"
	method="get">
	<div id="page_border">
		<img id="sign_in_icon" src="resources/Person Icon.png" /> <input
			id="sign_in_email" type="text" placeholder="E-Mail/UserID"> <input
			id="sign_in_password" type="text" placeholder="Password"> <input
			type="image" id="york_logo" src="resources/YCP Logo.png" />
	</div>
</form>
<body>
	<h3>Participants:</h3>
	<c:if test="${!empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>
	<form action="${pageContext.servletContext.contextPath}/classRoster"
		method="post">
		<table>
			<tr>
				<td>Screen Name</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Points</td>
				<td>Email</td>
			</tr>

			<c:forEach items="${users}" var="users">
				<tr>
					<td>${users.screenName}</td>
					<td>${users.firstName}</td>
					<td>${users.lastName}</td>
					<td>${users.points}</td>
					<td>${users.email}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>

</html>