<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<nav>
		    <a href="${pageContext.request.contextPath}/pocetnaStrana">Pocetna</a>
		    <a href="${pageContext.request.contextPath}/izlozbe">Izlozbe</a>
		    <a href="${pageContext.request.contextPath}/slike">Slike</a>
		    <a href="${pageContext.request.contextPath}/umetnici">Umetnici</a>
		
		    <sec:authorize access="isAnonymous()">
		        <a href="${pageContext.request.contextPath}/login">Ulogujte se</a>
		    </sec:authorize>
		
		    <sec:authorize access="hasRole('ADMIN')">
		        <a href="${pageContext.request.contextPath}/adminPanel">Admin Panel</a>
		    </sec:authorize>
		
		    <sec:authorize access="hasRole('USER')">
		        <a href="${pageContext.request.contextPath}/narudzbina">Vaša Korpa (<c:out value="${not empty sessionScope.korpa ? sessionScope.korpa.size() : 0}" />)</a>
		    </sec:authorize>
		
		    <sec:authorize access="isAuthenticated()">
		        <a href="${pageContext.request.contextPath}/logout">Logout</a>
		    </sec:authorize>
	         
	</nav>
</body>
	
</html>