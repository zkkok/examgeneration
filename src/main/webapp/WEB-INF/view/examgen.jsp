<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

	<title>Secure Exam Generation </title>
</head>
<body>

	<h2> Exam generation functionality available to lecturers and students </h2>
	
	<hr>
	
	<form action="generatequestions" method="GET">
		<input type="text" name ="areaofexam" placeholder="What's the area you request?"/>
		<input type="submit"/>
	</form>
	
	<h3>${message}</h3>

	
	<a href ="${pageContext.request.contextPath}/"> Back to Home Page </a> 
	
</body>
</html>