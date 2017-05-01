<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/landingPage.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Landing Page</title>
	<div id="page_border">
<a href ="${pageContext.servletContext.contextPath}/userPage">
			<c:if test="${empty user}">
			<input type = "image" src="resources/Person Icon.png" width="80px" height="80px"/>
			
			
		</c:if>
		<c:if test="${!empty user}">
			<input type="image" id="userIcon" src="${user.iconLocation}" width="80px" height="80px"/>
			</c:if>
	</a>
	<a href ="${pageContext.servletContext.contextPath}/landingPage">
		<img   id="york_logo" src="resources/YCP Logo.png"/>
		</a>
		
	</div>

<div id="overall_container">
	<div class="inputContainer">
		<div>
			<h2>Upload</h2>
		</div>
		<form action="${pageContext.servletContext.contextPath}/uploadPage" method="get">
			<div>
				<input class="linkIcon" type="image"
					src="resources/Upload Icon.png"/>
			</div>
		</form>

	</div>
	<div class="inputContainer">
		<div>
			<h2>Search Videos</h2>
		</div>

		<form action="${pageContext.servletContext.contextPath}/searchPage" method="get">
			<div>
				<input class="linkIcon" type="image"
					src="resources/Class Videos Icon.png"/>
			</div>
		</form>
	</div>
	<div class="inputContainer">
		<div>
			<h2>Assignments</h2>
		</div>

		<form action="${pageContext.servletContext.contextPath}/assignPage" method="get">
			<div>
				<input class="linkIcon" type="image"
					src="resources/Assignments Icon.png"/>
			</div>
		</form>
	</div>
	<div class="inputContainer">
		<div>
			<h2>Class Roster</h2>
		</div>

		<form action="${pageContext.servletContext.contextPath}/classRoster" method="get">
			<div>
				<input class="linkIcon" type="image"
					src="resources/Class Roster Icon.png"/>
			</div>
		</form>
	</div>
</div>

</html>