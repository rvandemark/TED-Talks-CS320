<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="CSS/loginPage.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Upload Page</title>


<div id="page_border">
	<img id="sign_in_icon" src="resources/Person Icon.png"/>
	<input type="image" id="york_logo" src="resources/YCP Logo.png"/>
</div>


<h2>Login to Account</h2>

<!-- got help with how to set up this and the servlet page from W3 schools and the resource provide -->
<c:if test="${!empty model.errorMessage}">
	<a class="error">${model.errorMessage}</a>
</c:if>
<form action="${pageContext.servletContext.contextPath}/loginPage" method="post">
	<div id="padding">
		<div>
			<label><b>Email</b></label>
		</div>

		<div>
			<input id="enter" type="text" placeholder="Enter Email" name="email" value="${model.email}" required/>
		</div>

		<div>
			<label><b>Password</b></label>
		</div>
		<div>
			<input id="enter" type="password" placeholder="Enter Password" name="password" required/>
		</div>
		<div>
			<button class="stylized" type="Submit" name="submit" value="login">Login</button>
		</div>
	</div>
</form>
<form action="${pageContext.servletContext.contextPath}/createAccount" method="get">
	<div>
		<p>
			Don't have an account?
			<button class="textButton" name="createAccount">Create account here</button>.
		</p>
	</div>
</form>
</html>