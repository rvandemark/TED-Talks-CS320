<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="CSS/createAccount.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Upload Page</title>

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

<h2>Create Account</h2>

<!-- got help with how to set up this page from W3 schools -->
<div id="padding">
 
 <div>
 	<label><b>First Name</b></label>
 </div>
 	  <div>
    <input id="enter" type="text" placeholder="Enter First Name" name="fname" required>
 </div>
 <div>
 	<label><b>Last Name</b></label>
 </div>
 	  <div>
    <input id="enter" type="text" placeholder="Enter Last Name" name="lname" required>
 </div>
 
 <div>
    <label><b>Email</b></label>
  </div>
  
  <div>
    <input id="enter" type="text" placeholder="Enter Email" name="email" required>
 </div>
 
 <div>
<label><b>Password</b></label>
</div>
<div>
    <input id="enter" type="password" placeholder="Enter Password" name="password" required>
</div>
<div>
    <label><b>Repeat Password</b></label>
</div>

<div>
    <input id="enter" type="password" placeholder="Repeat Password" name="passwordRepeat" required>
</div>

<div>    
    <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
</div>
    <div class="clearfix">
      
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>
  </div>
</html>