<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" href="CSS/videoDisplay.css">
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Video Display</title>

<body>

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
			<input type="Submit" value="Go to video!">
		</form>
	</c:if>
	<c:if test="${!empty video}">
		<div class="videoTitleWrapper" onclick="goToURL('${video.url}');">
			<h1 class="videoTitle">${video.name}</h1>
		</div>
		<iframe src="${video.embedUrl}" width="640px" height="360px"></iframe>
		<textarea id="reviewComment" placeholder="Leave a review!" cols=32
			rows=16></textarea>
		<img id="reviewStars" src="resources/Five Stars Blank.png" onclick="clicked(this);">
		<input id="reviewSubmit" type="submit" value="Submit Review!">
		<br>
		<div id="ratingContainer">
			<img id="ratingStars" src="resources/Five Stars Large.png"
				style="${video.ratingClip}" />
		</div>
	</c:if>

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/videoDisplay.js"></script>

</body>

</html>