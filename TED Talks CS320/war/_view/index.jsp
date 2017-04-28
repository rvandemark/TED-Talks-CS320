<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/index.css"/>
</head>
<title>YCP TED Talks</title>

<body>
	<div id="page_border">
		<c:if test="${empty user}">
			<img src="resources/Person Icon.png" width="80px" height="80px"/>
			<input id="sign_in_email" type="text" placeholder="E-Mail/UserID">
			<input id="sign_in_password" type="text" placeholder="Password">
			
		</c:if>
		<c:if test="${!empty user}">
			<img id="userIcon" src="${user.iconLocation}" width="80px" height="80px"/>
			<a id="user_greeting">Welcome, ${user.screenName}!</a>
		</c:if>
		<img id="york_logo" src="resources/YCP Logo.png"/>
	</div>
	<form action="${pageContext.servletContext.contextPath}/index" method="get">
		<input id="ted_talks_logo" type="image" src="resources/TED Talks Logo.png" />
	</form>

	<br>

</body>
</html>