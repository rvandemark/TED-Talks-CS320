<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/assignPage.css"/>
</head>

<meta charset="ISO-8859-1">

<title>TTB 0.0.1 Assignments Page</title>

	
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
	



<body>
	<div id="assign_box">
		<h1>Class Assignments</h1>
		<c:if test="${empty error}">
			<form action="${pageContext.servletContext.contextPath}/assignPage" method="post">
				<fieldset>
					<legend>Add a New Assignment</legend>
					<label>Name: </label>
						<input name="assignName" type="text"/>
					<label>Due Date: </label>
						<input name="dueDate" type="date"/> <br><br>
					<label>Assignment Type: </label> <br>
						<input class="assignTypeBtn" type="submit" name="specificVideo" value="Specific Video Name" disabled>
						<input class="assignTypeBtn" type="submit" name="disciplineVideos" value="Number By Discipline"> <br><br>
					<label>Video Name: </label>
							<input name="videoName" type="text" size="42"/> <br><br>
					<input type="submit" value="Add Assignment!"></input>
				</fieldset>
			
				<input type="hidden" name="specificVideoIsHidden" value="disabled">
			</form>
		</c:if>
		<c:if test="${!empty error}">
			<form action="${pageContext.servletContext.contextPath}/assignPage" method="post">
				<fieldset>
					<legend>Add a New Assignment</legend>
					<label>Name: </label>
						<input name="assignName" type="text" value="${inputName}"/>
					<label>Due Date: </label>
						<input name="dueDate" type="date" value="${inputDate}"/> <br><br>
					<label>Assignment Type: </label> <br>
						<input class="assignTypeBtn" type="submit" name="specificVideo" value="Specific Video Name" ${specificVideoDisabled}>
						<input class="assignTypeBtn" type="submit" name="disciplineVideos" value="Number By Discipline" ${disciplineVideosDisabled}> <br><br>
					<c:if test="${specificVideoDisabled == 'disabled'}">
						<label>Video Name: </label>
							<input name="videoName" type="text" size="42"/>
					</c:if>
					<c:if test="${disciplineVideosDisabled == 'disabled'}">
						<label>Discipline: </label>
							<select name="videoDiscipline">
								<c:if test="${empty videoDiscipline}">
									<option disabled selected> --- select a discipline --- </option>
								</c:if>
							    <c:forEach var="discipline" items="${disciplines}">
							        <option ${discipline == videoDiscipline ? 'selected' : ''}><c:out value="${discipline}"/></option>
							    </c:forEach>
							 </select> <br><br>
						<label>Number: </label>
							<input name="videoCount" type="number" min="1"/>
					</c:if>
					<br><br><a class="error">*Error: ${error}</a><br><br>
					<input type="submit" value="Add Assignment!"></input>
				</fieldset>
				
				<input type="hidden" name="specificVideoIsHidden" value="${specificVideoDisabled}">
				<input type="hidden" name="disciplineVideosIsHidden" value="${disciplineVideosDisabled}">
			</form>
		</c:if>
		<br>
		<c:if test="${fn:length(assignments) == '0'}">
			<p class="error">No current assignments</p>
		</c:if>
		<c:if test="${fn:length(assignments) != '0'}">
			<fieldset>
				<legend>Existing Assignments</legend>
				<c:forEach var="assignment" items="${assignments}">
					<p>${assignment.summary}</p>
				</c:forEach>
			</fieldset>
		</c:if>
	</div>
</body>

</html>