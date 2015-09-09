<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MainPage</title>
</head>
<body>
	<h1>Поставщики и потребители по регионам.</h1>
	
	<form action="report">	
		<table>
		<c:forEach var="region" items="${requestScope.regions}">
			<tr> 
				<td><input type="checkbox" name="region" value="${region}"/> ${region}</td>
			<!-- <a href="<c:url value="report?region=${region}"/>"><c:out value="${region}" /></a> -->
			</tr>
		</c:forEach>
		<tr>
			<td><input type="submit" value="Составить отчёт"></td>
		</tr>
		</table>		
					
	</form>
</body>
</html>