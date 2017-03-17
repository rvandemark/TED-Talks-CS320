<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" href="CSS/index.css">
</head>
<title>YCP TED Talks</title>

<body>
	<div id="page_border">
		<img id="sign_in_icon" src="resources/Person Icon.png"/>
		<input id="sign_in_email" type="text" placeholder="E-Mail or UserID">
		<input id="sign_in_password" type="text" placeholder="Password">
		<img id="york_logo" src="resources/YCP Logo.png"/>
	</div>
	<form action="${pageContext.servletContext.contextPath}/index"
		method="get">
		<input id="ted_talks_logo" type="image" src="resources/TED Talks Logo.png"/>
	</form>

	<br>

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>