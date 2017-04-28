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
		<img id="userIcon" src="${user.iconLocation}" width="80px"
			height="80px" /> <img id="york_logo" src="resources/YCP Logo.png" />
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

			<label>Add Discipline: </label> <select name="videoDiscipline">

				<option disabled selected>--- select a discipline ---</option>

				<c:forEach var="discipline" items="${disciplines}">
					<option ${discipline == videoDiscipline ? 'selected' : ''}><c:out
							value="${discipline}" /></option>
				</c:forEach>
			</select> <br>
			<br>
			<fieldset id="Assignments">
				<legend>Assignments</legend>
				<br>
			</fieldset>

			<fieldset id="Assignments">
				<legend>Classes</legend>
				<br>
			</fieldset>
			<div class="center">
				<button id="logOut" name="logOut">Log Out</button>
			</div>
		</div>
	</div>

	<br>

</body>
</html>