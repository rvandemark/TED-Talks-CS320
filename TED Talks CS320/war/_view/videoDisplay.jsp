<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/videoDisplay.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Video Display</title>

<body>

	<div id="reviewPopUpBackground"></div>

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
		
	
			<div id="searchBar">
				<input name="keywords" type="text" placeholder="Enter keywords...">
				<select name="filter">
					<option disabled selected value="null">Filter by...</option>
					<option value="all">All</option>
					<option value="title">Video Title</option>
					<option value="speaker">Speaker Name</option>
					<option value="discipline">Discipline</option>
				</select>
				<select name="sort">
					<option disabled selected value="null">Sort by...</option>
					<option value="highestRated">Highest Rated</option>
					<option value="lowestRated">Lowest Rated</option>
					<option value="recentUpload">Most Recent Upload</option>
					<option value="oldestUpload">Least Recent Upload</option>
				</select>
				<input name="search" type="submit" value="Search!">
			</div>
		</div>
	</form>

	<br>

	<c:if test="${empty video}">
		<form action="${pageContext.servletContext.contextPath}/videoDisplay" method="post">
			<input type="Submit" value="Go to video!">
		</form>
	</c:if>
	<c:if test="${!empty video}">
		<form action="${pageContext.servletContext.contextPath}/videoDisplay" method="post">
			<div id="reviewPopUp">
				<div>
			    	<input type="checkbox" name="positiveOptions" value="mustSee">
			    	<label for="mustSee">Must See!</label>
			    </div>
			    <div>
			    	<input type="checkbox" name="positiveOptions" value="greatForEngineer">
			    	<label for="greatForEngineer">Great for an Engineer!</label>
			    </div>
				<div>
			    	<input type="checkbox" name="positiveOptions" value="greatForAnyone">
			    	<label for="greatForAnyone">Great for Anyone!</label>
			    </div>
			    <div>
			    	<input type="checkbox" name="positiveOptions" value="interesting">
			    	<label for="interesting">Interesting, you will learn something new!</label>
			    </div>
			    <div>
			    	<input type="checkbox" name="positiveOptions" value="limited">
			    	<label for="limited">Very specialized, limited applicability.</label>
			    </div>
			    <br>
			    <input type="radio" name="negativeOptions" value="outdated"> Outdated
			    <input type="radio" name="negativeOptions" value="notRecommended"> Not recommended
			    <br>
			    <br>
			    <input id="finalSubmitReview" class="fancyBtn submit" type="submit" value="Submit!">
			    <button id="closeReview" class="fancyBtn close" type="button" value="Close">Close</button>
			</div>
		</form>
		<div class="suggestedVideosContainer">
			<c:forEach var="suggestion" items="${suggested}" varStatus="iter">
				<c:if test="${suggestion.name != video.name}">
					<form action="${pageContext.servletContext.contextPath}/videoDisplay" method="post">
						<div class="suggestedVideoWrapper">
							<input type="image" src="${suggestion.thumbnailUrl}" name="suggestedNo${iter.index}" width="200px" height="133px">
							<p class="suggestedVideoName">${suggestion.concatenatedName}</p>
						</div>
					</form>
				</c:if>
			</c:forEach>
		</div>
		<div class="videoTitleWrapper" onclick="goToURL('${video.url}')">
			<h1 class="indent videoTitle">${video.name}</h1>
		</div>
		<iframe src="${video.embedUrl}" width="640px" height="360px" allowfullscreen></iframe>
		<span id="subReviewWrapper">
			<textarea id="reviewComment" placeholder="Jot down notes for your review!" cols=32 rows=16></textarea>
			<img id="reviewStars" src="resources/Five Stars Blank.png">
			<input id="initReviewSubmit" class="fancyBtn submit" type="submit" value="Submit...">
		</span>
		<br>
		<div class="indent large videoTitleWrapper">
			<a class="large"><strong>Speaker: </strong></a>
			<form class="textButtonWrapper" action="${pageContext.servletContext.contextPath}/videoDisplay" method="post">
				<button class="textButton large" name="speakerNo0">${video.speaker}</button>
			</form>
		</div>
		<div class="indent videoTitleWrapper">
			<a class="large"><strong>Disciplines: </strong></a>
			<c:forEach var="discipline" items="${video.disciplines}" varStatus="loop">
				<form class="textButtonWrapper" action="${pageContext.servletContext.contextPath}/videoDisplay" method="post">
					<c:if test="${loop.index < fn:length(video.disciplines)-1}">
						<button class="textButton large" name="disciplineNo${loop.index}">${discipline}, </button>
					</c:if>
					<c:if test="${loop.index == fn:length(video.disciplines)-1}">
						<button class="textButton large" name="disciplineNo${loop.index}">${discipline}</button>
					</c:if>
				</form>
			</c:forEach>
		</div>
		<br>
		<c:if test="${video.numRatings != 0}">
			<img class="indent" id="ratingStars" src="resources/Five Stars Large.png" style="${video.ratingClip}" />
		</c:if>
		<c:if test="${video.numRatings == 0}">
			<a class="large indent">Be the first to rate this video!</a>
		</c:if>
	</c:if>
	
	<br><br><br><br><br>
	
	<h1 class="indent">Reviews (${fn:length(video.reviews)})</h1>
	<c:forEach var="review" items="${video.reviews}">
		<div class="reviewContent">
			<c:if test="${review.author.iconStatus eq true}">
				<img src="${review.author.iconLocation}" width="60px" height="60px">
			</c:if>
			<c:if test="${review.author.iconStatus eq false}">
				<img src="resources/Person Icon.png" width="60px" height="60px">
			</c:if>
			<a class="reviewName"><strong>${review.author.screenName}</strong></a> <br><br>
			<a class="reviewText">${review.text}</a>
		</div>
		<br>
	</c:forEach>
	
</body>

<script type="text/javascript" src="js/videoDisplay.js"></script>

</html>