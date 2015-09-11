<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Report</title>
</head>
<body>
	<h2>Отчёт по регионам</h2>
	<a href="">На главную</a>
	<table border="1" style="width:100%">
		<tr>
			<th>Регион</th>
			<th>Товар</th>
			<th>Производитель</th>
			<th>Потребитель</th>
		</tr>
		<c:forEach var="reportRecord" items="${requestScope.reportData}">
			<tr>
				<td>${reportRecord.region}</td>
				<td>${reportRecord.product.title}</td>
				<td>${reportRecord.producer.title}</td>
				<td>${reportRecord.consumer.title}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>