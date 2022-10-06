<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

	<title>Secure Exam Generation </title>
</head>
<body>

	<h2> Question and answer functionality available to lecturers only </h2>
	
	<hr>
	
	<form:form action="${pageContext.request.contextPath}/processForm" modelAttribute="question">
	Question(*):<form:input path="question"/>
	<form:errors path="question" cssClass="error"/>
	<br><br>
	Answer(*):<form:input path="answer"/>
	<form:errors path="answer" cssClass="error"/>
	<br><br>
	Area(*):<form:input path="area"/>
	<form:errors path="area" cssClass="error"/>
	<br><br>	
	<input type="submit" value="Submit"/>
	</form:form>
	
	<h3>${message}</h3>
	
	<a href ="${pageContext.request.contextPath}/"> Back to Home Page </a> 
	
</body>
</html>