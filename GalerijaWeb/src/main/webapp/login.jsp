<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="navigacija.jsp" />
<head>
<meta charset="UTF-8">
<title>Galerija</title>
</head>
	<body>
		<h2>Login</h2>
			<%--
			<form action="${pageContext.request.contextPath}/korisnik/login" method="post">
			    Unesiite email/korisnicko ime: <input type="text" name="identifier"><br>
			    Unesite sifru: <input type="password" name="sifra"><br>
			    <button type="submit">Login</button>
			</form>
			--%>
			
			<form action="${pageContext.request.contextPath}/login" method="post">
			    Unesiite email/korisnicko ime: <input type="text" name="username"><br>
			    Unesite sifru: <input type="password" name="password"><br>
			    <button type="submit">Login</button>
			</form>
			<br>
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
			<a href="${pageContext.request.contextPath}/registracija">Registruj se</a>
	
	</body>
</html>