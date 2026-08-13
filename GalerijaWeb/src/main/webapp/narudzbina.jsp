<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="navigacija.jsp" />
<head>
<meta charset="UTF-8">
<title>Korpa</title>
</head>
<body>
	<h1>Slike u korpi</h1>
	
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
	<table border="1">
		<tr>
			<th>Ime</th>
			<th>Umetnik</th>
			<th>Tehnika</th>
			<th>Cena</th>
			<th>Status</th>
		</tr>

		<c:forEach var="s" items="${slikeUKorpi}">
			<tr>
				<td>${s.ime}</td>
				<td>${s.umetnik.ime}</td>
				<td>${s.tehnika}</td>
				<td>${s.cena}</td>
				<td><img
					src="${pageContext.request.contextPath}/${s.urlSlike}"
					width="200" /></td>
			</tr>
		</c:forEach>
	</table>
	<h3>Ukupna cena: ${ukupnaCena} €</h3>
	<form action="${pageContext.request.contextPath}/narudzbina/zavrsiNarudzbinu" method= "post"><button type = "submit">Zavrsi Kupovinu i istampaj racun</button>
	
	</form>
</body>
</html>