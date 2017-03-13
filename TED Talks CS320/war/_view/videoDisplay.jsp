<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" href="CSS/videoDisplay.css">
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Video Display</title>

<form action="${pageContext.servletContext.contextPath}/index"
	method="get">
	<div id="page_border">
		<input type="image"
			src="https://cstest.s3.amazonaws.com/teams/392/york-college-pennsylvania-logo.jpg"
			id="york_logo" />
	</div>
</form>

<br>

<c:if test="${empty video}">
	<form action="${pageContext.servletContext.contextPath}/videoDisplay"
		method="post">
		<input type="Submit" name="submit" value="Go to video!">
	</form>		
</c:if>
<c:if test="${!empty video}">
	<div class="video_title_wrapper">
		<h1 class="video_title">${video.name}</h1>
	</div>
	<iframe src="${video.url}" width="640px" height="360px"></iframe>
	<br>
	<div class="container">
		<img id="clipped" src="resources/five stars.png"
			style="${video.ratingClip}" />
	</div>
</c:if>



</html>