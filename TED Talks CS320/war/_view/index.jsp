<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="get">
			<input type="Submit" name="add" value="Add Numbers!">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/multiplyNumbers" method="get">
			<input type="Submit" name="multiply" value="Multiply Numbers!">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/guessingGame" method="get">
			<input type="Submit" name="guessingGame" value="Guessing Game!">
		</form>
	</body>
</html>
