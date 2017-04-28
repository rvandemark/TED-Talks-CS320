<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="CSS/searchPage.css" />
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Search Page</title>

<form action="${pageContext.servletContext.contextPath}/index" method="get">
	<div id="page_border">
		<img id="sign_in_icon" src="resources/Person Icon.png"/>
		<input id="sign_in_email" type="text" placeholder="E-Mail/UserID">
		<input id="sign_in_password" type="text" placeholder="Password">
		<input id="york_logo" type="image" src="resources/YCP Logo.png"/>
	</div>
</form>

<br><br>

<div id="searchBar">
	<form action="${pageContext.servletContext.contextPath}/searchPage" method="post">
		<input name="keyword" type="text" placeholder="Enter keywords...">
		<select name="filter">
			<option disabled selected value="null">Filter by...</option>
			<option value="all">All</option>
			<option value="title">Video Title</option>
			<option value="speaker">Speaker Name</option>
			<option value="discipline">Discipline</option>
		</select>
		<select name="sort">
			<option disabled selected value="null">Sort by...</option>
			<option value="high">Highest Rated</option>
			<option value="low">Lowest Rated</option>
			<option value="new">Newest</option>
			<option value="old">Oldest</option>
		</select>
		<br><br>
		<input name="search" type="submit" value="Search!">
	</form>
</div>

<c:if test="${!empty model}">
	<br>
	<c:if test="${!empty model.results}">
		<c:if test="${fn:length(model.results) != '0'}">
			<div class="resultsContainer">
			<c:forEach var="result" items="${model.results}" varStatus="iter">
				<br>
				<form action="${pageContext.servletContext.contextPath}/searchPage" method="post">
					<div class="resultsWrapper">
						<input type="image" src="${result.thumbnailUrl}" name="searchNo${iter.index}" width="300px" height="200px">
						<div class="resultsInfo">
							<a>${result.fancyUploadDate}</a>
							<br><br>
							<a>${result.rating} / 5.0</a>
						</div>
						<div class="resultsInfo">
							<a>${result.speaker}</a>
							<br><br>
							<a>${result.concatenatedName}</a>
						</div>
					</div>
				</form>
			</c:forEach>
		</div>
		</c:if>
	</c:if>
	<c:if test="${empty model.results}">
		<div class="error"><a>There were no results found with those search criteria!</a></div>
	</c:if>
</c:if>
	
</html>