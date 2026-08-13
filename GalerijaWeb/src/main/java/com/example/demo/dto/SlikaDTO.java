package com.example.demo.dto;

import model.Umetnik;

public class SlikaDTO {
	private Integer cena;
	private String ime;
	private String tehnika;
	private String status;
	private Integer idSlika;
	private Integer umetnikId;
	private Umetnik umetnik;
	private String urlSlike;
	
	
	public Integer getIdSlika() {
		return idSlika;
	}
	public void setIdSlika(Integer idSlika) {
		this.idSlika = idSlika;
	}
	public Integer getUmetnikId() {
		return umetnikId;
	}
	public void setUmetnikId(Integer umetnikId) {
		this.umetnikId = umetnikId;
	}
	public Integer getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getTehnika() {
		return tehnika;
	}
	public void setTehnika(String tehnika) {
		this.tehnika = tehnika;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUmetnik(Umetnik umetnik) {
		this.umetnik = umetnik;
	}
	public Umetnik getUmetnik() {
		return umetnik;
	}
	public String getUrlSlike() {
		return urlSlike;
	}
	public void setUrlSlike(String urlSlike) {
		this.urlSlike = urlSlike;
	}

}
