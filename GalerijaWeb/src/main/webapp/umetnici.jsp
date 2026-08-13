<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="navigacija.jsp" />
<head>
<meta charset="UTF-8">
<title>Umetnici</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Ime</th>
			<th>Mesto Rodjenja</th>
			<th>Datum Rodjenja</th>
			<th>Datum Smrti</th>
			<th>Biografija</th>
		</tr>

		<c:forEach var="u" items="${umetnici}">
			<tr>
				<td>${u.ime}</td>
				<td>${u.mestoRodjenja}</td>
				<td>${u.datumRodjenja}</td>
				<td>${u.datumSmrti}</td> 
				<td>${u.biografija}</td> 

			</tr>
		</c:forEach>
	</table>
</body>
</html>