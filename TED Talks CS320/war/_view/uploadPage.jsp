<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/uploadPage.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Upload Page</title>

<form action="${pageContext.servletContext.contextPath}/index" method="get">
	<div id="page_border">
		<img id="sign_in_icon" src="resources/Person Icon.png" />
		<input id="sign_in_email" type="text" placeholder="E-Mail/UserID">
		<input id="sign_in_password" type="text" placeholder="Password">
		<input type="image" id="york_logo" src="resources/YCP Logo.png"/>
		
	</div>
</form>

<form action="${pageContext.servletContext.contextPath}/uploadPage" method="post">
<div id="myTable">
	<h1>Upload TED Talk</h1>
	<tr>
		<td class="label">Video Url:</td>
		<input type="text" name="video_url" size="20" value="${video_url}" />
	</tr>

	<input type="Submit" name="submit" value="Submit URL">
</div>
</form>
</html>