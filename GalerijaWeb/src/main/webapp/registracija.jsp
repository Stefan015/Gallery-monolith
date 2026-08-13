<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registracija</title>
</head>
<body>

	<h2>Registracija</h2>

	<form action="${pageContext.request.contextPath}/korisnik/registracija" method="post">
		email: <input type="text" name="email"><br> 
		korsnicko ime: <input type="text" name="korisnickoIme"><br>
		sifra: <input type="password" name="sifra"><br>
		<button type="submit">Registruj se</button>
	</form>
	<br>
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
	<a href="${pageContext.request.contextPath}/login">Uloguj se</a>

</body>
</html>