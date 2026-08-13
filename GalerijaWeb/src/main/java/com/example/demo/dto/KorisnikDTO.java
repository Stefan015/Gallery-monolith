package com.example.demo.dto;

public class KorisnikDTO {
	private Integer idKorisnik;
	private String identifier;
	private String korisnickoIme;
	private String sifra;
	private String email;
	private String uloga;
	
	public Integer getIdKorisnik() {
		return idKorisnik;
	}
	public void setIdKorisnik(Integer idKorisnik) {
		this.idKorisnik = idKorisnik;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	};
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
