<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/userPage.css" />
</head>
<title>YCP TED Talks</title>

<body>
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


	<div>

		<h1 id="user_greeting">Welcome, ${user.screenName}!</h1>
		<h3 id="points">Total Points: ${user.points }</h3>
		<div>
			<fieldset id="Disciplines">
				<legend id="Discipline">Disciplines</legend>

				<c:forEach items="${user.disciplines}" var="discipline"
					varStatus="loop">
					<form action="${pageContext.servletContext.contextPath}/userPage"
						method="post">
						<button id="delete">X</button>
						<label name="buttonNo${loop.index}"> ${loop.count}.
							${discipline} </label><br>
					</form>
				</c:forEach>
			</fieldset>
<form action="${pageContext.servletContext.contextPath}/userPage" method="post">
			<label>Add Discipline: </label> <select name="videoDiscipline">

				<option disabled selected>--- select a discipline ---</option>

				<c:forEach var="discipline" items="${disciplines}">
					<option ${discipline == videoDiscipline ? 'selected' : ''}><c:out
							value="${discipline}" /></option>
				</c:forEach>
			</select> <br>
			<br>
			
			
			<input  type="submit" value="Add Discipline!" name = "addDiscipline"></input>
			</form>
			<fieldset id="Assignments">
				<legend>Assignments</legend>
				<br>
			</fieldset>

			<fieldset id="Assignments">
				<legend>Classes</legend>
				<br>
			</fieldset>
			<div class="center">
			<form action="${pageContext.servletContext.contextPath}/logout" method="post">
				<button id="logOut" name="logOut">Log Out</button>
				</form>
			</div>
		</div>
	</div>

	<br>

</body>
</html>