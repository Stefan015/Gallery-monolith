package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class NarudzbinaDTO {

    private Integer idNarudzbina;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datum;
    private int ukupnaCena;

    private Integer korisnikId;
    private String korisnickoIme;

    private List<SlikaDTO> slike;

	public Integer getIdNarudzbina() {
		return idNarudzbina;
	}

	public void setIdNarudzbina(Integer idNarudzbina) {
		this.idNarudzbina = idNarudzbina;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(int ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Integer getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public List<SlikaDTO> getSlike() {
		return slike;
	}

	public void setSlike(List<SlikaDTO> slike) {
		this.slike = slike;
	}

  
}