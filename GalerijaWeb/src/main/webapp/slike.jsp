<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="navigacija.jsp" />
<head>
<meta charset="UTF-8">
<title>Slike</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Ime</th>
			<th>Umetnik</th>
			<th>Tehnika</th>
			<th>Cena</th>
			<th>Status</th>
		</tr>

		<c:forEach var="s" items="${listaSlika}">
			<tr>
				<td>${s.ime}</td>
				<td>${s.umetnik.ime}</td>
				<td>${s.tehnika}</td>
				<td>${s.cena}€</td>
				<td>
				${s.status} <br>
				<c:if test ="${s.status eq 'dostupno'}">
					<c:if test="${sessionScope.uloga eq 'korisnik'}">
						<c:choose>
						    <c:when test="${korpa.contains(s.idSlika)}">
						        U korpi
						    </c:when>
						
						    <c:otherwise>
						        <a href="${pageContext.request.contextPath}/narudzbina/dodajUkorpu?idSlika=${s.idSlika}">
						            dodaj u korpu
						        </a>
						    </c:otherwise>
						</c:choose>
					</c:if>
				</c:if>
				</td>
				<td><img
					src="${pageContext.request.contextPath}/${s.urlSlike}" width="300" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>