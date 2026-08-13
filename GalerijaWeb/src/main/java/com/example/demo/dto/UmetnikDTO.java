package com.example.demo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UmetnikDTO {
	
	private Integer idUmetnik;
	
	private String ime;
	
	private String mestoRodjenja;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumRodjenja;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumSmrti; 

    private String biografija;

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMestoRodjenja() {
		return mestoRodjenja;
	}

	public void setMestoRodjenja(String mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Date getDatumSmrti() {
		return datumSmrti;
	}

	public void setDatumSmrti(Date datumSmrti) {
		this.datumSmrti = datumSmrti;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String briografija) {
		this.biografija = briografija;
	}
    
	public Integer getIdUmetnik() {
		return idUmetnik;
	}
	
	public void setIdUmetnik(Integer idUmetnik) {
		this.idUmetnik = idUmetnik;
	}
}
