<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="navigacija.jsp" />
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
</head>
<body>
	<%-- Deo za Korisnike ---------------------------------------------------------------------------------- --%>
	<a href="${pageContext.request.contextPath}/adminPanel?adminView=Korisnici"><h1>Korisnici</h1></a>
	<c:if test="${adminView eq 'Korisnici' or adminView eq 'KorisniciPromena'}">
		<h3>Dodavanje Korisnika</h3>
		<form action="${pageContext.request.contextPath}/korisnik/dodajKorisnika" method="post">
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
			Korisnicko Ime: <input type="text" name="korisnickoIme" /> <br> 
			Email: <input type="text" name="email" /> <br> 
			Sifra: <input type="password" name="sifra" /> <br> 
			Uloga: <select name="uloga">
				<option value="admin">Admin</option>
				<option value="korisnik">Korisnik</option>
				</select>
			<c:if test="${not empty poruka}">
		    	<p style="color:black;">${poruka}</p>
			</c:if>
			<br>
			<button type="submit">Dodaj Korisnika</button>
		</form>
		<h3>Brisanje Korisnika</h3>
		<form action="${pageContext.request.contextPath}/korisnik/obrisiKorisnika" method="post" >
			<select name = "idKorisnik">
				<c:forEach var ="k" items= "${korisnici}">
					<option value="${k.idKorisnik}">${k.korisnickoIme}</option>
				</c:forEach>
			</select>
			<button type="submit">Obrisi Izabranog Korisnika</button>
		</form>
		<h3>Izmena Uloge Korisnika</h3>
		<form action="${pageContext.request.contextPath}/korisnik/odabirKorisnikaZaIzmenu" method="get" >
			<select name = "idKorisnik">
				<c:forEach var ="k" items= "${korisnici}">
					<option value="${k.idKorisnik}">${k.korisnickoIme}</option>
				</c:forEach>
			</select>
			<button type="submit">Izaberi korisnika za promenu</button>
		</form>
		<c:if test="${adminView eq 'KorisniciPromena'}">
		<form action="${pageContext.request.contextPath}/korisnik/promeniUloguKorisniku" method="post" >
		
			<input type="hidden" name="idKorisnik" value="${korisnikZaPromenu.idKorisnik}">
			Ime odabranog korisnika: ${korisnikZaPromenu.korisnickoIme}
			<select name="uloga">
				<option value="admin">Admin</option>
				<option value="korisnik">Korisnik</option>
			</select>
			<button type = "submit">Sacuvajte unete promene</button>
		</form>	
		</c:if>
	</c:if>
		
	<%-- Deo za Slike ---------------------------------------------------------------------------------- --%>
	<a href="${pageContext.request.contextPath}/adminPanel?adminView=Slike"><h1>Slike</h1></a>
	<c:if test="${adminView eq 'Slike' }">
		<h3>Dodavanje Slike</h3>
		<form action="${pageContext.request.contextPath}/slika/dodajSliku" method="post" enctype="multipart/form-data">
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
			Ime Slike: <input type="text" name="ime" /> <br> 
			Umetnik: <select name="umetnikId">
				<c:forEach var="u" items="${umetnici}">
					<option value="${u.idUmetnik}">${u.ime}</option>
				</c:forEach>
			</select> <br> 
			Tehnika: <input type="text" name="tehnika" /> <br>
			Cena: <input type="number" name="cena" /> <br> 
			Status: <select name="status">
				<option value="dostupno">Dostupno</option>
				<option value="prodato">Nedostupno</option>
			</select> <br> 
			Slika: <input type="file" name="slikaPath" /> <br>
			<button type="submit">Dodaj Sliku</button>
		</form>
		<h3>Brisanje Slike</h3>
		<form action="${pageContext.request.contextPath}/slika/obrisiSliku" method="post" >
			<select name = "idSlika">
				<c:forEach var ="s" items= "${slike}">
					<option value="${s.idSlika}">${s.ime}</option>
				</c:forEach>
			</select>
			<button type="submit">Obrisi Izabranu Sliku</button>
		</form>
	</c:if>
	<%-- Deo za Izlozbe ---------------------------------------------------------------------------------- --%>
	<a href="${pageContext.request.contextPath}/adminPanel?adminView=Izlozbe"><h1>Izlozbe</h1></a>
	<c:if test="${adminView eq 'Izlozbe' or adminView eq 'IzlozbePromena' }">
	
		<h3>Dodavanje Izlozbe</h3>
		<form action="${pageContext.request.contextPath}/izlozba/dodajIzlozbu" method="post" enctype="multipart/form-data">
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
			Naslov izlozbe: <input type="text" name="naslov" /> <br> 
			Opis izlozbe: <textarea name="opis"></textarea> <br> 
			Datum pocetka <input type ="date" name="datumPocetka"/> <br>
			Datum kraja <input type ="date" name="datumKraja"/> <br>
			<select name="umetnikIds" multiple>
			    <c:forEach var="u" items="${umetnici}">
			        <option value="${u.idUmetnik}"> ${u.ime}</option>
			    </c:forEach>
			</select> <br>
			<button type="submit">Dodaj Izlozbu</button>
		</form>
		<h3>Brisanje Izlozbe</h3>
		<form action="${pageContext.request.contextPath}/izlozba/obrisiIzlozbu" method="post" >
			<select name = "idIzlozbe">
				<c:forEach var ="i" items= "${izlozbe}">
					<option value="${i.idIzlozbe}">${i.naslov}</option>
				</c:forEach>
			</select>
			<button type="submit">Obrisi Izabranu Izlozbu</button>
		</form>
		<h3>Izmena Izlozbe</h3>
		<form action="${pageContext.request.contextPath}/izlozba/odabirIzlozbeZaPromenu" method="get" >
			<select name = "idIzlozbe">
				<c:forEach var ="i" items= "${izlozbe}">
					<option value="${i.idIzlozbe}">${i.naslov}</option>
				</c:forEach>
			</select>
			<button type="submit">Izaberi Izlozbu za promenu</button>
		</form>
		<c:if test="${adminView eq 'IzlozbePromena'}">
		<form action="${pageContext.request.contextPath}/izlozba/sacuvajPromenuIzlozbe" method="post" >
		
			<input type="hidden" name="idIzlozbe" value="${izlozbaZaPromenu.idIzlozbe}">
			Naslov izlozbe: <input type="text" name="naslov" value = "${izlozbaZaPromenu.naslov}"/> <br> 
			Opis izlozbe: <textarea name="opis">${izlozbaZaPromenu.opis}</textarea> <br> 
			Datum pocetka <input type ="date" name="datumPocetka" value = "${izlozbaZaPromenu.datumPocetka }"/><br>
			Datum kraja <input type ="date" name="datumKraja" value = "${izlozbaZaPromenu.datumKraja}"/> <br>
			<select name="umetnikIds" multiple>
			    <c:forEach var="u" items="${umetnici}">
			        <option value="${u.idUmetnik}"
			           <c:forEach var = "izUmetnik" items= "${izlozbaZaPromenu.umetnici}">
			           		<c:if test = "${izUmetnik.idUmetnik == u.idUmetnik }">
			           			selected
			           		</c:if>
			           </c:forEach> 
			          >${u.ime}
			        </option>
			    </c:forEach>
			</select>
			<button type = "submit">Sacuvajte unete promene</button>
		</form>	
		</c:if>
	</c:if>
	<%-- Deo za Umetnike ---------------------------------------------------------------------------------- --%>
	<a href="${pageContext.request.contextPath}/adminPanel?adminView=Umetnici"><h1>Umetnici</h1></a>
	<c:if test="${adminView eq 'Umetnici' }">
		<h3>Dodavanje Umetnika</h3>
		<form action="${pageContext.request.contextPath}/umetnik/dodajUmetnika" method="post">
			<c:if test="${not empty poruka}">
				<p>${poruka}</p>
			</c:if>
			Ime Umetnika: <input type="text" name="ime" /> <br> 
			Mesto Rodjenja: <input type="text" name=mestoRodjenja /> <br> 
			Datum Rodjenja: <input type="date" name="datumRodjenja" /> <br> 
			Datum Smrti: <input type="date" name="datumSmrti" /> <br> 
			Biografija: <textarea name="biografija"></textarea>
			<br>
			<button type="submit">Dodaj Umetnika</button>
		</form>
		<h3>Brisanje Umetnika</h3>
		<form action="${pageContext.request.contextPath}/umetnik/obrisiUmetnika" method="post" >
			<select name = "idUmetnik">
				<c:forEach var ="u" items= "${umetnici}">
					<option value="${u.idUmetnik}">${u.ime}</option>
				</c:forEach>
			</select>
			<button type="submit">Obrisi Izabranog Umetnika</button>
		</form>
	</c:if>
</body>
</html>