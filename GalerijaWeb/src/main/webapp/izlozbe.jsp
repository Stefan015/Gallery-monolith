<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="navigacija.jsp" />
<head>
<meta charset="UTF-8">
<title>Izlozbe</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Naslov</th>
			<th>Opis</th>
			<th>Datum Pocetka</th>
			<th>Datum Kraja</th>
			<th>Umetnici</th>
		</tr>

		<c:forEach var="i" items="${izlozbe}">
			<tr>
				<td>${i.naslov}</td>
				<td>${i.opis}</td>
				<td>${i.datumPocetka}</td>
				<td>${i.datumKraja}</td>
				<td>
					<c:forEach var="u" items = "${i.umetnici}">
						${u.ime}<br/>
					</c:forEach>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>