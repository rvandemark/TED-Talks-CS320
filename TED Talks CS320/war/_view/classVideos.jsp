<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="CSS/classVideos.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Class Videos</title>

<form action="${pageContext.servletContext.contextPath}/index" method="get">
	<div id="page_border">
		<img id="sign_in_icon" src="resources/Person Icon.png" />
		<input id="sign_in_email" type="text" placeholder="E-Mail/UserID">
		<input id="sign_in_password" type="text" placeholder="Password">
		<input type="image" id="york_logo" src="resources/YCP Logo.png"/>
	</div>
</form>

<c:if test="${!empty YCPClasses}">
	<div>
		<c:forEach items="${YCPClasses}" var="name">
		    <p class="class_name">${name}</p>
		</c:forEach>
	</div>
</c:if>
</html>